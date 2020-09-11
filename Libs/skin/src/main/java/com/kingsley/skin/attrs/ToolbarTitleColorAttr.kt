package com.kingsley.skin.attrs

import android.view.View
import androidx.appcompat.widget.Toolbar
import com.kingsley.skin.SkinElementAttr
import com.kingsley.skin.SkinManager

/**
 * Toolbar
 *
 * app:titleTextColor="@color/color"
 * app:subtitleTextColor="@color/color"
 */
class ToolbarTitleColorAttr : SkinElementAttr() {

    companion object {
        const val TAG = "ToolbarTitleColorAttr"

        fun registerSkinAttr(){
            for (attrName in listOf("titleTextColor", "subtitleTextColor")) {
                SkinManager.registerSkinAttr("attrName", ToolbarTitleColorAttr::class.java)
            }
        }
    }

    override fun apply(view: View?) {
        (view as? Toolbar)?.run {
            when (attrName) {
                "titleTextColor" -> {
                    setTitleTextColor(SkinManager.getColorStateList(context, attrValueRefId))
                }
                "subtitleTextColor" -> {
                    setSubtitleTextColor(SkinManager.getColorStateList(context, attrValueRefId))
                }
            }
        }
    }
}