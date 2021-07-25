package com.kingsley.skin.attrs

import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.ColorInt
import com.kingsley.skin.SkinElementAttr
import com.kingsley.skin.SkinManager

/**
 * View
 * android:background
 */
class BackgroundAttr(
    /**
     * 动态设置的 color
     */
    @ColorInt
    var dynamicBgColor: Int = 0,
    /**
     * 动态设置的 Drawable
     */
    var dynamicBgDrawable: Drawable? = null
) : SkinElementAttr() {

    companion object {
        const val TAG = "BackgroundAttr"
    }

    override fun apply(view: View?) {
        super.apply(view)

        view?.run {
            when{
                dynamicBgColor != 0 -> setBackgroundColor(dynamicBgColor)
                dynamicBgDrawable != null -> background = dynamicBgDrawable
                attrValueTypeName == "color"  -> setBackgroundColor(SkinManager.getColor(context, attrValueRefId))
                attrValueTypeName == "drawable" -> background = SkinManager.getDrawable(context, attrValueRefId)
            }
        }
    }
}