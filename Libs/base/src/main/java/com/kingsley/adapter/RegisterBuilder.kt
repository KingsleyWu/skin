package com.kingsley.adapter

/**
 * Created by kingsley on 2020/9/25
 */

class RegisterBuilder<T>(private val clazz: Class<T>, private val adapter: MultiTypeAdapter){

    /**
     * 设置 Delegate
     * @param delegates Delegate
     */
    @SafeVarargs
    fun register(vararg delegates: ItemViewDelegate<T, *>): MultiTypeAdapter {
        for (delegate in delegates) {
            adapter.register(clazz, delegate)
        }
        return adapter
    }

}