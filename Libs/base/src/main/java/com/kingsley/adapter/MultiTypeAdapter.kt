package com.kingsley.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kotlin.reflect.KClass

/**
 * Created by kingsley on 2020/9/23
 */
open class MultiTypeAdapter @JvmOverloads constructor(open var items: List<Any> = emptyList()) :
    Adapter<ViewHolder>() {
    private val helper: Helper by lazy { Helper() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return getDelegate(viewType).onCreateViewHolder(parent.context, parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getDelegate(holder.itemViewType).onBindViewHolder(holder, items[position])
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        getDelegate(holder.itemViewType).onBindViewHolder(holder, items[position], payloads)
    }

    override fun getItemCount(): Int = items.size

    override fun getItemId(position: Int): Long {
        return getDelegate(getItemViewType(position)).getItemId(items[position])
    }

    override fun onViewRecycled(holder: ViewHolder) {
        getDelegate(holder.itemViewType).onViewRecycled(holder)
    }

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        getDelegate(holder.itemViewType).onViewAttachedToWindow(holder)
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        getDelegate(holder.itemViewType).onViewDetachedFromWindow(holder)
    }

    override fun getItemViewType(position: Int): Int {
        val data = items.getOrNull(position)
        data?.let {
            val viewType = helper.types[data.javaClass] ?: indexOf(position, data)
            viewType?.let {
                if (viewType != -1) {
                    return viewType
                }
            }
        }
        //找不到匹配的 viewType
        throw NullPointerException(
            "No holder added that matches at position=$position in data source, class name =${data?.javaClass}"
        )
    }

    private fun indexOf(position: Int, data: Any): Int? {
        val function = helper.oneToManyFun[data.javaClass]
        function?.let {
            helper.oneToManyDelegateMap[it(position, data)]?.let {
                return helper.delegates.indexOfValue(it)
            }
        }
        return null
    }

    private fun getDelegate(viewType: Int): ItemViewDelegate<Any, ViewHolder> {
        @Suppress("UNCHECKED_CAST")
        return helper.delegates[viewType] as ItemViewDelegate<Any, ViewHolder>
    }

    /**
     * 此方法用于直接设置内容
     */
    fun setData(data: List<Any>) {
        items = if (items === data) ArrayList(data) else data
        notifyDataSetChanged()
    }

    /**
     * 注册 一对一 delegate
     *
     * tips： 此方法会覆盖 之前一对多 注册的 delegate
     */
    fun <T> register(clazz: Class<T>, delegate: ItemViewDelegate<T, *>) {
        RegisterBuilder(clazz, helper).delegate(delegate).doRegister()
    }

    inline fun <reified T : Any> register(delegate: ItemViewDelegate<T, *>) {
        register(T::class.java, delegate)
    }

    fun <T : Any> register(clazz: KClass<T>, delegate: ItemViewDelegate<T, *>) {
        register(clazz.java, delegate)
    }

    fun <T : Any> with(clazz: KClass<T>): OneToManyRegister<T> {
        return with(clazz.java)
    }

    /**
     * 注册 一对多 delegate
     * tips： 此方法会覆盖 之前一对一 注册的 delegate
     */
    fun <T : Any> with(clazz: Class<T>): OneToManyRegister<T> {
        return OneToManyRegisterBuilder(clazz, helper)
    }
}