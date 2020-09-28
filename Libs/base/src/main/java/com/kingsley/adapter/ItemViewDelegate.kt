package com.kingsley.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by kingsley on 2020/9/23
 */
abstract class ItemViewDelegate<T, VH : RecyclerView.ViewHolder>{

    fun onCreateViewHolder(context: Context, parent: ViewGroup): VH =
         onCreateViewHolder(LayoutInflater.from(context), parent)

    abstract fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): VH

    abstract fun onBindViewHolder(holder: VH, item: T)

    open fun onBindViewHolder(holder: VH, item: T, payloads: List<T>) {
        onBindViewHolder(holder, item)
    }

    fun getPosition(holder: RecyclerView.ViewHolder): Int  = holder.adapterPosition

    open fun onViewRecycled(holder: VH) {}

    open fun onViewAttachedToWindow(holder: VH) {}

    open fun onViewDetachedFromWindow(holder: VH) {}

    open fun getItemId(item: T): Long = RecyclerView.NO_ID

    /**
     * 用于 区分 一个class 对应多个 delegate 的方法
     */
    open fun isMach(item: T) : Boolean = true
}