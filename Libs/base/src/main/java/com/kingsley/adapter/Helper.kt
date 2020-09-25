package com.kingsley.adapter

import androidx.collection.SparseArrayCompat

/**
 * Created by kingsley on 2020/9/25
 */
internal class Helper {
    /** 所有的 Delegate */
    val delegates: SparseArrayCompat<ItemViewDelegate<*, *>> by lazy { SparseArrayCompat() }

    /** 一对一的 type */
    val types: MutableMap<Class<*>, Int> by lazy { mutableMapOf() }

    /** 一对多的 Delegate class */
    val oneToManyDelegateViewType: SparseArrayCompat<Class<*>> by lazy { SparseArrayCompat() }

    /** 一对多的 class 对应的 viewType */
    val oneToManyClazzViewType: MutableMap<Class<*>, MutableList<Int>> by lazy { mutableMapOf() }

    /** 一对多的 class(此class 为 ItemViewDelegate 的class )对应的 ItemViewDelegate */
    val oneToManyDelegateMap: MutableMap<Class<*>, ItemViewDelegate<*, *>> by lazy { mutableMapOf() }

    /** 一对多的type 方法 */
    val oneToManyFun: MutableMap<Class<*>, (position: Int, item: Any) -> Class<out ItemViewDelegate<*, *>>> by lazy {
        mutableMapOf()
    }
}