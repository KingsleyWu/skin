package com.kingsley.skin.attrs

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import com.kingsley.skin.SkinElementAttr
import com.kingsley.skin.SkinManager
import com.kingsley.skin.util.L

/**
 * AppCompatAutoCompleteTextView
 *
 * android:popupBackground
 */
class AutoCompleteAttr(
    /**
     * 动态设置的 popupBackground Drawable
     */
    var dynamicPopupBackgroundDrawable: Drawable? = null
) : SkinElementAttr() {

    companion object {
        const val TAG = "AutoCompleteAttr"

        fun registerSkinAttr(){
            SkinManager
                .registerSkinStyle("popupBackground", android.R.attr.popupBackground)
                .registerSkinAttr("popupBackground", AutoCompleteAttr::class.java)
        }
    }

    override fun apply(view: View?) {
        super.apply(view)
        L.d(TAG, "applyView:$view, this: $this")

        (view as? AppCompatAutoCompleteTextView)?.run {
            when  {
                dynamicPopupBackgroundDrawable != null -> setDropDownBackgroundDrawable(dynamicPopupBackgroundDrawable)
                attrName == "popupBackground" -> setDropDownBackgroundDrawable(SkinManager.getDrawable(context, attrValueRefId))
            }
        }
    }
}