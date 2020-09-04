package com.kingsley.simple

import android.app.Application
import com.kingsley.simple.attrs.CardBackgroundColorAttr
import com.kingsley.skin.SkinElementAttrFactory
import com.kingsley.skin.SkinManager

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        SkinManager.init(this)
        // 注册自定义的换肤属性
        SkinElementAttrFactory.registerSkinAttr(
            "cardBackgroundColor",
            CardBackgroundColorAttr::class.java
        )
    }
}