package com.kingsley.skin.attrs

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.ColorInt
import androidx.cardview.widget.CardView
import com.kingsley.skin.SkinElementAttr
import com.kingsley.skin.SkinManager

/**
 * CardView
 *
 * android:cardBackgroundColor
 */
class CardBackgroundColorAttr(
    /**
     * 动态设置的 color
     */
    @ColorInt
    var dynamicCardBackgroundColor: Int = 0,
    /**
     * 动态设置的 ColorStateList
     */
    var dynamicColorStateList: ColorStateList? = null
) : SkinElementAttr() {

    companion object {
        const val TAG = "CardBackgroundColorAttr"

        fun registerSkinAttr() {
            SkinManager
                .registerSkinAttr("cardBackgroundColor", CardBackgroundColorAttr::class.java)
                .registerSkinStyle(
                    "cardBackgroundColor",
                    androidx.cardview.R.styleable.CardView_cardBackgroundColor,
                )
        }
    }

    override fun apply(view: View?) {
        (view as? CardView)?.run {
            when{
                dynamicCardBackgroundColor != 0 -> setCardBackgroundColor(dynamicCardBackgroundColor)
                dynamicColorStateList != null -> setCardBackgroundColor(dynamicColorStateList)
                else -> setCardBackgroundColor(SkinManager.getColorStateList(context, attrValueRefId))
            }
        }
    }

}