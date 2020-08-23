package com.kingsley.base.mvp

abstract class BaseModel {
    abstract fun onCreateModel()
    abstract fun onDestroyModel()

    fun destroy(){
        onDestroyModel()
    }
}