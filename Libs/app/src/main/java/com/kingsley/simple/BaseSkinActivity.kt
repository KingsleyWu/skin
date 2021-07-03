package com.kingsley.simple

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.SkinAppCompatDelegateImpl
import com.kingsley.skin.listener.OnSkinChangedListener

/**
 * @author Kingsley
 * Created on 2021/7/2.
 * 帶換膚的 默認工具類
 */
open class BaseSkinActivity: AppCompatActivity(), OnSkinChangedListener {

    override fun getDelegate() = SkinAppCompatDelegateImpl[this,
            super.getDelegate()
    ]

}