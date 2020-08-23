package com.kingsley.base.mvp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

abstract class BaseView {
    var mActivity : FragmentActivity? = null
    var mFragment : Fragment? = null

    constructor(activity: FragmentActivity){
        mActivity = activity
    }

    constructor(fragment: Fragment){
        mFragment = fragment
        mActivity = fragment.activity
    }

    @Suppress("UNCHECKED_CAST")
    open fun <T : FragmentActivity?> getActivity(): T {
        return mActivity as T
    }

    @Suppress("UNCHECKED_CAST")
    open fun <T : Fragment?> getFragment(): T {
        return mFragment as T
    }

    abstract fun onDestroyView()
}