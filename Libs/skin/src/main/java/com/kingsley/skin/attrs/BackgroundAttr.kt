package com.kingsley.skin.attrs

import android.view.View
import com.kingsley.skin.SkinElementAttr
import com.kingsley.skin.SkinManager

/**
 * View
 * android:background
 */
class BackgroundAttr : SkinElementAttr() {

    override fun apply(view: View?) {
        super.apply(view)

        view?.run {
            when (attrValueTypeName) {
                "color" -> setBackgroundColor(SkinManager.getColor(context, attrValueRefId))
                "drawable" -> background = (SkinManager.getDrawable(context, attrValueRefId))
            }
        }
    }
}