package com.kingsley.skin.attrs

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.annotation.ColorInt
import com.kingsley.skin.SkinElementAttr
import com.kingsley.skin.SkinManager

/**
 * ImageView
 * android:src
 */
class ImageSrcAttr(
    /**
     * 动态设置的 Drawable
     */
    var dynamicImageDrawable: Drawable? = null,
    /**
     * 动态设置的 Bitmap
     */
    var dynamicImageBitmap: Bitmap? = null
) : SkinElementAttr() {

    companion object {
        const val TAG = "ImageSrcAttr"
    }

    override fun apply(view: View?) {
        super.apply(view)
        Log.d(TAG, "applyView:$view, this: $this")
        (view as? ImageView)?.run {
            when{
                dynamicImageDrawable != null -> setImageDrawable(dynamicImageDrawable)
                dynamicImageBitmap != null -> setImageBitmap(dynamicImageBitmap)
                else -> setImageDrawable(SkinManager.getDrawable(context, attrValueRefId))
            }
        }
    }
}