package com.kingsley.simple.attrs

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

        fun getAttrNames() : List<String>{
            return listOf("titleTextColor", "subtitleTextColor")
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