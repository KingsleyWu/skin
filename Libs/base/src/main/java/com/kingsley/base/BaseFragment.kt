package com.kingsley.base

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import java.util.*

abstract class BaseFragment : Fragment(), IBaseView {

    val STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN"
    val mClickListener = View.OnClickListener { v -> onDebouncingClick(v) }
    var mActivity : FragmentActivity? = null
    var mInflater : LayoutInflater? = null
    var mContentView : View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = activity
        val fm = fragmentManager ?: return
        if (savedInstanceState != null) {
            val isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN)
            val ft: FragmentTransaction = fm.beginTransaction()
            if (isSupportHidden) {
                ft.hide(this)
            } else {
                ft.show(this)
            }
            ft.commitAllowingStateLoss()
        }
        initData(arguments)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mInflater = inflater
        mContentView = inflater.inflate(getLayoutId(), container, false)
        return mContentView
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, false)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
    }
}
