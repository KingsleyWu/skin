package com.kingsley.skin.util

import android.app.Activity
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.TextView
import com.kingsley.skin.SkinElementAttr
import com.kingsley.skin.SkinManager

fun TextView.textColor(textColor: Int) {
    activity?.let {
        SkinManager.addTextColorSkinAttr(it, this, textColor)
    }
}

fun TextView.textColor(textColor: ColorStateList) {
    activity?.let {
        SkinManager.addTextColorSkinAttr(it, this, textColor)
    }
}

fun TextView.textColorHint(textColorHint: Int) {
    activity?.let {
        SkinManager.addTextColorHintSkinAttr(context as Activity, this, textColorHint)
    }
}

fun TextView.textColorHint(textColorHint: ColorStateList) {
    activity?.let {
        SkinManager.addTextColorHintSkinAttr(context as Activity, this, textColorHint)
    }
}

fun View.backgroundColor(bgColor: Int) {
    activity?.let {
        SkinManager.addBackgroundSkinAttr(context as Activity, this, bgColor)
    }
}

fun TextView.background(bgDrawable: Drawable) {
    activity?.let {
        SkinManager.addBackgroundSkinAttr(context as Activity, this, bgDrawable)
    }
}

fun View.addDynamicSkinAttr(createSkinElementAttr: () -> SkinElementAttr?) {
    activity?.let {
        SkinManager.addDynamicSkinAttr(context as Activity, this, createSkinElementAttr)
    }
}

fun View.addSkinAttr(attrName: String, value: Int) {
    activity?.let {
        SkinManager.addSkinAttr(context as Activity, this, attrName, value)
    }
}

inline val View.activity
    get() = context as? Activity?