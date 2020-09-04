package com.kingsley.simple

import android.app.Application
import com.kingsley.simple.attrs.CardBackgroundColorAttr
import com.kingsley.simple.attrs.ToolbarTitleColorAttr
import com.kingsley.skin.SkinManager

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        SkinManager.init(this)

        // 注册自定义的换肤属性
        for (attrName in CardBackgroundColorAttr.getAttrNames()) {
            SkinManager.registerSkinAttr(attrName, CardBackgroundColorAttr::class.java)
        }
        for (attrName in ToolbarTitleColorAttr.getAttrNames()) {
            SkinManager.registerSkinAttr(attrName, ToolbarTitleColorAttr::class.java)
        }
    }
}