package com.kingsley.adapter

/**
 * Created by kingsley on 2020/9/25
 */

internal class OneToManyRegisterBuilder<T>(private val clazz: Class<T>, private val helper: Helper) : OneToManyRegister<T>{

    /** 一对多的 Delegate */
    private var delegates: Array<ItemViewDelegate<T, *>>? = null

    /**
     * 设置 一对多的 Delegate
     * @param delegates 一对多的 Delegate
     */
    @SafeVarargs
    override fun delegates(vararg delegates: ItemViewDelegate<T, *>): OneToManyRegister<T> {
        @Suppress("UNCHECKED_CAST")
        this.delegates = delegates as Array<ItemViewDelegate<T, *>>
        return this
    }

    /**
     * 注册 Delegate
     * @param getDelegateClazz 获取当前item 所对应的 Delegate.class 方法
     */
    override fun register(getDelegateClazz: (position: Int, item: T) -> Class<out ItemViewDelegate<T, *>>) {
        unregisterIfNeeded()
        // 保存获取对应 delegate class的方法
        @Suppress("UNCHECKED_CAST")
        helper.oneToManyFun[clazz] =
            getDelegateClazz as (position: Int, item: Any) -> Class<out ItemViewDelegate<Any, *>>
        doRegister()
    }

    private fun doRegister() {
        val viewTypeList = mutableListOf<Int>()
        for (delegate in delegates!!) {
            var viewType: Int =
                helper.oneToManyDelegateViewType.indexOfValue(delegate.javaClass)
            if (viewType == -1) {
                viewType = helper.delegates.size()
                while (helper.delegates.get(viewType) != null) {
                    viewType++
                }
            }
            helper.oneToManyDelegateMap[delegate.javaClass] = delegate
            helper.oneToManyDelegateViewType.put(viewType, delegate.javaClass)
            helper.delegates.put(viewType, delegate)
            viewTypeList.add(viewType)
        }
        helper.oneToManyClazzViewType[clazz] = viewTypeList
    }

    private fun unregisterIfNeeded() {
        helper.types[clazz]?.let {
            helper.types.remove(clazz)
            helper.delegates.remove(it)
        }
    }
}