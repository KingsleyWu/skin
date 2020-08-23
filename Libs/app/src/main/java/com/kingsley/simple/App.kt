package com.kingsley.simple

import android.app.Application
import com.kingsley.skin.SkinManager
import skin.support.SkinCompatManager
import skin.support.app.SkinAppCompatViewInflater
import skin.support.app.SkinCardViewInflater
import skin.support.constraint.app.SkinConstraintViewInflater
import skin.support.design.app.SkinMaterialViewInflater


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        SkinManager.init(this)
        //initSkin()
    }

    private fun initSkin() {
        SkinCompatManager.withoutActivity(this)
            .addInflater(SkinAppCompatViewInflater()) // 基础控件换肤初始化
            .addInflater(SkinMaterialViewInflater()) // material design
            .addInflater(SkinConstraintViewInflater()) // ConstraintLayout
            .addInflater(SkinCardViewInflater()) // CardView
            .setSkinWindowBackgroundEnable(false) // 关闭windowBackground换肤
            .loadSkin()
    }
}