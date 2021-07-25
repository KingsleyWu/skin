package com.kingsley.skin.attrs

import android.content.res.ColorStateList
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorInt
import com.kingsley.skin.SkinElementAttr
import com.kingsley.skin.SkinManager

/**
 * TextView
 *
 * android:textColor
 */
class TextColorAttr(
    /**
     * 动态设置的 color
     */
    @ColorInt
    var dynamicTextColor: Int = 0,
    /**
     * 动态设置的 TextColor ColorStateList
     */
    var dynamicTextColorColorStateList: ColorStateList? = null,
    /**
     * 动态设置的 TextColorHint color
     */
    @ColorInt
    var dynamicTextColorHint: Int = 0,
    /**
     * 动态设置的 TextColorHint ColorStateList
     */
    var dynamicTextColorHintColorStateList: ColorStateList? = null
) : SkinElementAttr() {

    companion object {
        const val TAG = "TextColorAttr"
    }

    override fun apply(view: View?) {
        super.apply(view)
//        Log.d(TAG, "applyView:$view, this: $this")

        (view as? TextView)?.run {
            when{
                dynamicTextColor != 0 -> setTextColor(dynamicTextColor)
                dynamicTextColorColorStateList != null -> setTextColor(dynamicTextColorColorStateList)
                dynamicTextColorHint != 0 -> setHintTextColor(dynamicTextColorHint)
                dynamicTextColorHintColorStateList != null -> setHintTextColor(dynamicTextColorHintColorStateList)
                attrValueTypeName == "textColor"  -> setTextColor(SkinManager.getColorStateList(context, attrValueRefId))
                attrValueTypeName == "textColorHint" -> setHintTextColor(SkinManager.getColorStateList(context, attrValueRefId))
            }
        }
    }
}