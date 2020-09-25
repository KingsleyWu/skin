package com.kingsley.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction

abstract class BaseFragment : Fragment(), IBaseView {

    private val stateSaveIsHidden = "STATE_SAVE_IS_HIDDEN"
    val mClickListener = View.OnClickListener { v -> onDebouncingClick(v) }
    var mActivity : FragmentActivity? = null
    var mInflater : LayoutInflater? = null
    var mContentView : View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = activity
        val fm = parentFragmentManager
        if (savedInstanceState != null) {
            val isSupportHidden = savedInstanceState.getBoolean(stateSaveIsHidden)
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
        outState.putBoolean(stateSaveIsHidden, false)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
    }
}
