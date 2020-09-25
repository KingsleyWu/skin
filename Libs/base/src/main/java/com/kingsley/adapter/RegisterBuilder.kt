package com.kingsley.adapter

/**
 * Created by kingsley on 2020/9/25
 */
internal class RegisterBuilder<T>(private val clazz: Class<T>, private val helper: Helper) {

    /** 一对多的 Delegate */
    private lateinit var delegate: ItemViewDelegate<T, *>

    /**
     * 设置 一对多的 Delegate
     * @param delegate 一对多的 Delegate
     */
    fun delegate(delegate: ItemViewDelegate<T, *>): RegisterBuilder<T> {
        this.delegate = delegate
        return this
    }

    fun doRegister() {
        unregisterIfNeeded()
        var viewType: Int? = helper.types[clazz]
        if (viewType == null) {
            viewType = helper.delegates.size()
            while (helper.delegates.get(viewType) != null) {
                viewType++
            }
        }
        helper.types[clazz] = viewType
        helper.delegates.put(viewType, delegate)
    }

    private fun unregisterIfNeeded() {
        helper.oneToManyClazzViewType[clazz]?.let {
            helper.oneToManyFun.remove(clazz)
            for (i in it) {
                val delegateClazz = helper.oneToManyDelegateViewType[i]
                helper.oneToManyDelegateMap.remove(delegateClazz)
                helper.delegates.remove(i)
            }
        }
    }
}