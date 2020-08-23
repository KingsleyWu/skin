package com.kingsley.skin

import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.androidx.appcompat.app.ContextUtils
import com.kingsley.base.BuildConfig


/**
 * 一个LayoutInflater Factory
 *
 * 拦截所有XML中的View创建，检查View是否有换肤支持的属性，如果没有，则不拦截
 *
 */
class SkinInflaterFactory : LayoutInflater.Factory2 {

    var mAppCompatFactory: LayoutInflater.Factory2? = null
    var mInflater: LayoutInflater? = null

    companion object {
        private const val DEBUG = true
        const val TAG = "SkinInflaterFactory@@"
    }

    /**
     * Store the view item that need skin changing in the activity
     */
    internal val mSkinItems: MutableMap<View, SkinElement> = mutableMapOf()

    // if this is NOT enable to be skined , simplly skip it
    override fun onCreateView(parent: View?, name: String, context: Context, attrs: AttributeSet): View? {
        // 先收集属性，看是否有换肤支持的属性，如果没有，则不拦截
        val skinAttrs = parseSkinAttr(context, attrs)
        if (skinAttrs.isEmpty()) {
            return null
        }
        var view: View? = null
        if (mAppCompatFactory != null) {
            view = mAppCompatFactory?.onCreateView(parent, name, context, attrs)
        }
        if (view == null) {
            view = createView(name, ContextUtils.wrapContext(context, parent, attrs), attrs)
        }
        if (view != null) {
            Log.d(TAG, "onCreateView2 context:$context, name:$name $view")
            mSkinItems[view] = SkinElement(view, skinAttrs).apply {
                initApply()
            }
        }
        return view
    }

    // if this is NOT enable to be skined , simplly skip it
    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        // 先收集属性，看是否有换肤支持的属性，如果没有，则不拦截
        val skinAttrs = parseSkinAttr(context, attrs)
        if (skinAttrs.isEmpty()) {
            return null
        }
        val view = createView(name, context, attrs) ?: return null
        Log.d(TAG, "onCreateView1 context:$context, name:$name $view")
        mSkinItems[view] = SkinElement(view, skinAttrs).apply {
            initApply()
        }
        return view
    }

    private fun createView(name: String, context: Context, attrs: AttributeSet): View? {
        var view: View? = null
        try {
            if (mAppCompatFactory != null) {
                view = mAppCompatFactory?.onCreateView(name, context, attrs)
            }
            if (view == null) {
                if (-1 == name.indexOf('.')) { // 系统自带的widget
                    if ("View" == name) {
                        view = LayoutInflater.from(context).createView(name, "android.view.", attrs)
                    }
                    if (view == null) {
                        view = LayoutInflater.from(context).createView(name, "android.widget.", attrs)
                    }
                    if (view == null) {
                        view = LayoutInflater.from(context).createView(name, "android.webkit.", attrs)
                    }
                } else {
                    view = LayoutInflater.from(context).createView(name, null, attrs)
                }
            }
            if (BuildConfig.DEBUG) {
                Log.d(TAG, "onCreateView context:$context, name:$name $view")
            }
        } catch (e: Exception) {
            Log.e(TAG, "error while create 【" + name + "】 : " + e.message)
            view = null
        }
        return view
    }

    private fun parseSkinAttr(context: Context, attrs: AttributeSet): ArrayList<SkinElementAttr> {
        val viewAttrs: ArrayList<SkinElementAttr> = ArrayList()
        for (i in 0 until attrs.attributeCount) {
            val attrName = attrs.getAttributeName(i)
            val attrValue = attrs.getAttributeValue(i)
            if (!SkinElementAttrFactory.isSupportedAttr(attrName)) { // 看属性是否是支持换肤的属性
                continue
            }
            if (attrValue.startsWith("@")) { // 只有@开头的才表示用了引用资源
                try {
                    val id = attrValue.substring(1).toInt()
                    val entryName = context.resources.getResourceEntryName(id)
                    val typeName = context.resources.getResourceTypeName(id)
                    val mSkinAttr = SkinElementAttrFactory.createSkinAttr(attrName, id, entryName, typeName)
                    if (mSkinAttr != null) {
                        viewAttrs.add(mSkinAttr)
                    }
                } catch (e: NumberFormatException) {
                    e.printStackTrace()
                } catch (e: Resources.NotFoundException) {
                    e.printStackTrace()
                }
            }
        }
        return viewAttrs
    }

    fun applySkin() {
        mSkinItems.forEach {
            it.value.apply()
        }
    }

    fun clean() {
        mSkinItems.forEach {
            it.value.clean()
        }
        mSkinItems.clear()
    }
}