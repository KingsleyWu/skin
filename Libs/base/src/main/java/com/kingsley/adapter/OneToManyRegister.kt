package com.kingsley.adapter

/**
 * Created by kingsley on 2020/9/25
 */
interface OneToManyRegister<T> {

    /**
     * 设置 一对多的 Delegate
     * @param delegates 一对多的 Delegate
     */
    @SafeVarargs
    fun delegates(vararg delegates: ItemViewDelegate<T, *>): OneToManyRegister<T>

    /**
     * 注册 Delegate
     * @param getDelegateClazz 获取当前item 所对应的 Delegate.class 方法
     */
    fun register(getDelegateClazz: (position: Int, item: T) -> Class<out ItemViewDelegate<T, *>>)
}