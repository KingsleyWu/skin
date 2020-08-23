package com.kingsley.base.mvp

abstract class BasePresenter<V : BaseView> {

    var mView : V? = null

    abstract fun onAttachView()

    fun binView(view: V){
        mView = view
        onAttachView()
    }

    fun onDestroyPresenter(){
        mView?.onDestroyView()
    }
}