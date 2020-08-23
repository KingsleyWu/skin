package com.kingsley.base

import android.os.Bundle
import android.view.View


interface IBaseView {

    fun getLayoutId() : Int
    fun onDebouncingClick(view: View)
    fun initData(bundle: Bundle?)
}