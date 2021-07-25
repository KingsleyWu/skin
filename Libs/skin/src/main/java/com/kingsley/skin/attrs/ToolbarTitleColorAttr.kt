package com.kingsley.skin.attrs

import android.content.res.ColorStateList
import android.view.View
import androidx.annotation.ColorInt
import androidx.appcompat.widget.Toolbar
import com.kingsley.skin.SkinElementAttr
import com.kingsley.skin.SkinManager

/**
 * Toolbar
 *
 * app:titleTextColor="@color/color"
 * app:subtitleTextColor="@color/color"
 */
class ToolbarTitleColorAttr(
    /**
     * 动态设置的 titleTextColor color
     */
    @ColorInt
    var dynamicTitleTextColor: Int = 0,
    /**
     * 动态设置的 titleTextColor ColorStateList
     */
    var dynamicTitleTextColorColorStateList: ColorStateList? = null,
    /**
     * 动态设置的 subtitleTextColor color
     */
    @ColorInt
    var dynamicSubtitleTextColor: Int = 0,
    /**
     * 动态设置的 subtitleTextColor ColorStateList
     */
    var dynamicSubtitleTextColorColorStateList: ColorStateList? = null
) : SkinElementAttr() {

    companion object {
        const val TAG = "ToolbarTitleColorAttr"

        fun registerSkinAttr() {
            SkinManager.registerSkinStyle(
                "titleTextColor",
                androidx.appcompat.R.styleable.Toolbar_titleTextColor
            ).registerSkinStyle(
                "subtitleTextColor",
                androidx.appcompat.R.styleable.Toolbar_subtitleTextColor
            ).registerSkinAttr("titleTextColor", ToolbarTitleColorAttr::class.java)
                .registerSkinAttr("subtitleTextColor", ToolbarTitleColorAttr::class.java)
        }
    }

    override fun apply(view: View?) {
        (view as? Toolbar)?.run {
            when{
                dynamicTitleTextColor != 0 -> setTitleTextColor(dynamicTitleTextColor)
                dynamicTitleTextColorColorStateList != null -> setTitleTextColor(dynamicTitleTextColorColorStateList!!)
                dynamicSubtitleTextColor != 0 -> setSubtitleTextColor(dynamicSubtitleTextColor)
                dynamicSubtitleTextColorColorStateList != null -> setSubtitleTextColor(dynamicSubtitleTextColorColorStateList!!)
                attrName == "titleTextColor"  ->  setTitleTextColor(SkinManager.getColorStateList(context, attrValueRefId))
                attrName == "subtitleTextColor" -> setSubtitleTextColor(SkinManager.getColorStateList(context, attrValueRefId))
            }
        }
    }
}