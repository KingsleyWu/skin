package com.kingsley.skin.attrs

import android.view.View
import android.view.ViewGroup
import com.kingsley.skin.SkinElementAttr
import com.kingsley.skin.SkinManager

/**
 * View
 * android:marginX
 */
class MarginAttr : SkinElementAttr() {

    companion object {
        const val TAG = "MarginAttr"
    }

    override fun apply(view: View?) {
        super.apply(view)
        view?.run {
            val padding = SkinManager.getDimension(context, attrValueRefId).toInt()

            val lp = (layoutParams as? ViewGroup.MarginLayoutParams) ?: return
            when (attrName) {
                "layoutMargin" -> {
                    lp.run {
                        leftMargin = padding
                        topMargin = padding
                        rightMargin = padding
                        bottomMargin = padding
                    }
                }
                "layoutMarginLeft" -> {
                    lp.run {
                        leftMargin = padding
                    }
                }
                "layoutMarginTop" -> {
                    lp.run {
                        topMargin = padding
                    }
                }
                "layoutMarginRight" -> {
                    lp.run {
                        rightMargin = padding
                    }
                }
                "layoutMarginBottom" -> {
                    lp.run {
                        bottomMargin = padding
                    }
                }
                else -> {}
            }
        }
    }
}