package com.kingsley.skin

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.AssetManager
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.kingsley.skin.util.SkinSpUtils
import com.kingsley.skin.util.async
import com.kingsley.skin.util.runUIThread
import java.io.File
import java.lang.reflect.Field

/**
 * 皮肤管理器
 */
object SkinManager {

    private const val TAG = "SkinManager"

    private lateinit var mApplication: Application

    /**
     * 皮肤包的Resource
     */
    private var mSkinResources: Resources? = null

    /**
     * 通用的资源加载器
     */
    lateinit var skinResourcesProxy: SkinResourcesProxy

    /**
     * 前缀或后缀资源加载器
     */
    lateinit var mSkinSuffixResources: SkinSuffixResources

    /** skinPackageName */
    internal var skinPackageName: String? = null

    /**
     * 需要被变更的数据
     */
    private val mSkins: MutableMap<Activity, ActivitySkinChange> = mutableMapOf()

    /**
     * 防止快速切换
     */
    private var isApplying = false

    private var currentSkinPath: String? = null
    private var currentSkinName: String? = null
    private var isPathLoader = false

    /**
     * 当前皮肤组件是否进行了初始化
     */
    private val isInit: Boolean
        get() {
            return this::mApplication.isInitialized
        }

    /**
     *
     * 皮肤管理器的入口
     *
     * 初始化皮肤管理
     */
    fun init(context: Application) {
        mApplication = context
        // 把activity中所有的View收集起来
        addActivityCallback()
        // 如果用户设置了皮肤，则应用用户设置的皮肤
        applySkin()
    }

    /**
     *
     * 皮肤管理器的入口
     *
     * 初始化皮肤管理
     */
    fun init(context: Application, isPathSkin: Boolean = false, isPrefix: Boolean = false) {
        mApplication = context
        SkinConfig.isPathSkin = isPathSkin
        SkinConfig.isPrefix = isPrefix
        // 把activity中所有的View收集起来
        addActivityCallback()
        // 如果用户设置了皮肤，则应用用户设置的皮肤
        applySkin()
    }

    private fun applySkin() {
        // 如果设置了皮肤，加载设置的皮肤
        async {
            if (SkinConfig.isPathSkin) {
                applyPathSkin()
            } else{
                applyNameSkin()
            }
        }
    }

    private fun applyPathSkin(){
        // 加载资源的resource，有皮肤从皮肤中记载，没有默认的资源包加载
        skinResourcesProxy = SkinResourcesProxy(mApplication.resources)
        val skinPath = currentSkinPath()
        Log.d(TAG, "applyInitSkin skinPath:$skinPath")
        if (!skinPath.isNullOrEmpty()) {
            applySkin(skinPath)
        }
    }

    private fun applyNameSkin(){
        mSkinSuffixResources = SkinSuffixResources(mApplication.resources)
        mSkinSuffixResources.mSkinPkgName = mApplication.packageName
        val skinName = currentSkinName()
        Log.d(TAG, "applyInitSkin skinName:$skinName")
        if (!skinName.isNullOrEmpty()) {
            applySkinName(skinName)
        }
    }

    /**
     * 扩展自己的皮肤属性
     */
    fun registerSkinAttr(attrName: String, attr: Class<out SkinElementAttr>) {
        SkinElementAttrFactory.registerSkinAttr(attrName, attr)
    }

    /**
     * 当元素的属性值是通过代码设置的时候，需要手动把要换肤的元素和属性添加到皮肤框架中
     */
    fun addSkinAttr(activity: Activity, view: View, attrName: String, value: Int) {

        // 如果不支持，则直接退出
        if (!SkinElementAttrFactory.isSupportedAttr(attrName)) return

        val activitySkinChange = mSkins[activity] ?: return

        val inflaterFactory = activitySkinChange.mSkinInflaterFactory
        val skinItems = inflaterFactory.mSkinItems

        if (!skinItems.keys.contains(view)) {
            skinItems[view] = SkinElement(view)
        }

        val attrs = skinItems[view]?.attrs

        val entryName = activity.resources.getResourceEntryName(value)
        val typeName = activity.resources.getResourceTypeName(value)

        val skinElementAttr = SkinElementAttrFactory.createSkinAttr(attrName, value, entryName, typeName) ?: return

        attrs?.add(skinElementAttr)

        // 初始加进来的时候需要重新设置一下
        skinElementAttr.initApply(view)
    }


    /**
     * 该对象用来收集Activity里带有支持换肤属性的组件
     */
    private class ActivitySkinChange(activity: Activity) : SkinAble {
        val mSkinInflaterFactory = SkinInflaterFactory()

        init {
            mSkinInflaterFactory.mInflater = activity.layoutInflater
            if (activity is AppCompatActivity) {
                mSkinInflaterFactory.mAppCompatFactory = activity.layoutInflater.factory2
                setLayoutInflaterFactory(activity.layoutInflater)
                // 给ActivityLayoutInflater设置一个Factory来拦截所有的View创建
                activity.layoutInflater.factory = mSkinInflaterFactory
                setLayoutInflaterFactory(activity.layoutInflater)
                // 给ActivityLayoutInflater设置一个Factory来拦截所有的View创建
                activity.layoutInflater.factory2 = mSkinInflaterFactory
            } else {
                // 给ActivityLayoutInflater设置一个Factory来拦截所有的View创建
                activity.layoutInflater.factory = mSkinInflaterFactory
            }
            activity.isImmersive
        }

        fun setLayoutInflaterFactory(original: LayoutInflater) {
            try {
                val mFactorySet: Field = LayoutInflater::class.java.getDeclaredField("mFactorySet")
                mFactorySet.isAccessible = true
                mFactorySet.set(original, false)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        override fun onChange() {
            mSkinInflaterFactory.applySkin()
        }
    }


    private fun addActivityCallback() {

        // 注册所有的Activity的生命周期监听器
        //
        mApplication.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {

                //
                mSkins[activity] = ActivitySkinChange(activity)
            }

            override fun onActivityDestroyed(activity: Activity) {
                mSkins[activity]?.mSkinInflaterFactory?.clean()
                mSkins.remove(activity)
            }

            override fun onActivityPaused(activity: Activity) {
            }

            override fun onActivityStarted(activity: Activity) {
            }


            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            }

            override fun onActivityStopped(activity: Activity) {
            }

            override fun onActivityResumed(activity: Activity) {
            }

        })
    }

    /**
     *
     */
    internal fun getCurrentSkinResource(): Resources? {
        return this.mSkinResources
    }


    /**
     * 加载一个皮肤包
     *
     * @return 加载成功Resources返回的是皮肤包的Resources对象，String为失败原因
     */
    private fun loadSkin(skinPkgPath: String): Pair<Resources?, String?> {
        val context = mApplication

        val file = File(skinPkgPath)
        if (!file.exists()) {
            return Pair(null, "Skin file is not exist")
        }

        val pm = context.packageManager
        return try {
            val skinPackageInfo = pm.getPackageArchiveInfo(skinPkgPath, PackageManager.GET_ACTIVITIES)

            skinPackageName = skinPackageInfo?.packageName

            val skinAssetManager = AssetManager::class.java.newInstance()
            val addAssetPathMethod = skinAssetManager.javaClass.getMethod("addAssetPath", String::class.java)
            addAssetPathMethod.invoke(skinAssetManager, skinPkgPath)

            val resources: Resources = context.resources
            val skinResource = Resources(skinAssetManager, resources.displayMetrics, resources.configuration)

            Pair(skinResource, null)
        } catch (e: java.lang.Exception) {

            // 皮肤包加载失败
            Pair(null, e.message)
        }
    }


    /**
     * 当前的皮肤是否是默认皮肤
     */
    internal fun isDefaultSkin(): Boolean {
        return mSkinResources == null
    }

    /**
     * 应用皮肤
     *
     * @param skinPkgPath 外部皮肤包的路径，如果为null表示恢复成默认皮肤
     * @param changeCallback 回调
     */
    fun applySkin(skinPkgPath: String?, changeCallback: ILoaderListener? = null) {

        // 未初始化皮肤组件
        if (!isInit) {
            throw RuntimeException("You should init SkinManager by call init() method.")
        }

        // 防止快速切换
        if (isApplying) return

        Log.d(TAG, "applySkin:$skinPkgPath, current:$currentSkinPath")

        // 防止无效切换
        if ((isDefaultSkin() && skinPkgPath.isNullOrEmpty()) || skinPkgPath == currentSkinPath) {
            return
        }
        isApplying = true
        if (skinPkgPath.isNullOrEmpty()) {
            changeCallback?.onStart()
            restoreDefaultTheme()
            runUIThread {
                changeCallback?.onSuccess()
                isApplying = false
            }
        } else {
            isPathLoader = true
            Log.d(TAG, "applySkin:$skinPkgPath")
            async(
                preExecute = {
                    changeCallback?.onStart()
                },
                doBackground = {
                    loadSkin(skinPkgPath)
                },
                postExecute = {
                    val result = it!!

                    Log.d(TAG, "applySkinComplete:$skinPkgPath, result:$it")
                    if (result.first != null) { // 皮肤切换成功
                        // 保存皮肤路径
                        saveCurrentSkin(skinPkgPath)

                        // 加载皮肤包的Resource对象
                        mSkinResources = result.first

                        // 通知已经创建了界面换肤
                        notifySkinChange()

                        // 皮肤切换成功回调
                        changeCallback?.onSuccess()
                    } else { // 皮肤切换失败
                        changeCallback?.onFailed(result.second)
                    }

                    isApplying = false
                })
        }
    }

    /**
     * 应用皮肤
     *
     * @param skinName 内部皮肤名称
     * @param changeCallback 回调
     */
    fun applySkinName(skinName: String?, changeCallback: ILoaderListener? = null) {

        // 未初始化皮肤组件
        if (!isInit) {
            throw RuntimeException("You should init SkinManager by call init() method.")
        }

        // 防止快速切换
        if (isApplying) return

        Log.d(TAG, "applySkinName:$skinName, current:$currentSkinName")

        // 防止无效切换
        if ((isDefaultSkin() && skinName.isNullOrEmpty()) || skinName == currentSkinName) {
            return
        }

        isApplying = true
        if (skinName.isNullOrEmpty()) {
            changeCallback?.onStart()
            restoreDefaultTheme()
            runUIThread {
                changeCallback?.onSuccess()
                isApplying = false
            }
        } else {
            Log.d(TAG, "applySkin:$skinName")
            isPathLoader = false
            async(
                preExecute = {
                    changeCallback?.onStart()
                },
                doBackground = {
                    mSkinSuffixResources.mSkinName = skinName
                    mSkinSuffixResources.isDefaultSkin = false
                    Pair(mSkinSuffixResources.mResources, null)
                },
                postExecute = {
                    val result = it!!

                    Log.d(TAG, "applySkinComplete:$skinName, result:$it")
                    // 皮肤切换成功
                    // 保存皮肤路径
                    saveCurrentSkinName(skinName)
                    // 加载皮肤包的Resource对象
                    mSkinResources = result.first
                    // 通知已经创建了界面换肤
                    notifySkinChange()
                    // 皮肤切换成功回调
                    changeCallback?.onSuccess()
                    isApplying = false
                })
        }
    }

    fun restoreDefaultTheme() {
        if (mSkinResources != null) {
            mSkinResources = null
            isPathLoader = false
            mSkinSuffixResources.reset()
            saveCurrentSkin("")
            saveCurrentSkinName("")
            notifySkinChange()
        }
    }

    private fun saveCurrentSkin(skinPkgPath: String) {
        currentSkinPath = skinPkgPath
        val context = mApplication
        SkinSpUtils.putString(context, "skinPath", skinPkgPath)
    }

    /**
     * 保存当前应用的皮肤名称
     */
    private fun saveCurrentSkinName(skinName: String) {
        currentSkinName = skinName
        SkinSpUtils.putString(mApplication, "skinName", skinName)
    }

    /**
     * 当前应用的皮肤包
     */
    fun currentSkinPath(): String? {
        return SkinSpUtils.getString(mApplication, "skinPath")
    }

    /**
     * 当前应用的皮肤名称
     */
    fun currentSkinName(): String? {
        return SkinSpUtils.getString(mApplication, "skinName")
    }

    /**
     * 获取 color
     * @param context ctx
     * @param id resId
     */
    fun getColor(context: Context, id: Int): Int {
        return try {
            if (isPathLoader) {
                skinResourcesProxy.getColor(id)
            } else {
                mSkinSuffixResources.getColor(mApplication, id)
            }
        } catch (e: Exception) {
            ContextCompat.getColor(context, id)
        }
    }

    /**
     * 获取 ColorStateList
     * @param context ctx
     * @param id resId
     */
    fun getColorStateList(context: Context, id: Int): ColorStateList? {
        return try {
            if (isPathLoader) {
                skinResourcesProxy.getColorStateList(id)
            } else {
                mSkinSuffixResources.getColorStateList(mApplication, id)
            }
        } catch (e: Exception) {
            ContextCompat.getColorStateList(context, id)
        }
    }

    /**
     * 获取 Dimension
     * @param context ctx
     * @param id resId
     */
    fun getDimension(context: Context, id: Int): Float {
        return try {
            if (isPathLoader) {
                skinResourcesProxy.getDimension(id)
            } else {
                mSkinSuffixResources.mResources.getDimension(id)
            }
        } catch (e: Exception) {
            context.resources.getDimension(id)
        }
    }

    /**
     * 获取 Drawable
     * @param context ctx
     * @param id resId
     */
    fun getDrawable(context: Context, id: Int): Drawable? {
        return try {
            if (isPathLoader) {
                skinResourcesProxy.getDrawable(id)
            } else {
                mSkinSuffixResources.getDrawable(mApplication, id)
            }
        } catch (e: Exception) {
            ContextCompat.getDrawable(context, id)
        }
    }

    private fun notifySkinChange() {
        mSkins.forEach {
            it.value.onChange()
        }
    }

    interface ILoaderListener {
        fun onStart()
        fun onSuccess()
        fun onFailed(reason: String?)
    }
}