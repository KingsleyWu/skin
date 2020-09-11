package com.kingsley.skin

import com.kingsley.skin.attrs.*

/**
 * 在这里可以添加动态换肤的属性
 *
 */
object SkinElementAttrFactory {

    private val supportSkinAttrs: MutableMap<String, Class<out SkinElementAttr>> = mutableMapOf()

    private val mSkinStyle: MutableMap<Int, Map<String, Class<out SkinElementAttr>>> = mutableMapOf()

    /**
     * 添加一个换肤
     *
     * @param attrName
     * @param attrElementClazz
     *
     */
    fun registerSkinAttr(attrName: String, attrElementClazz: Class<out SkinElementAttr>) : SkinElementAttrFactory{
        supportSkinAttrs[attrName] = attrElementClazz
        return this
    }

    /**
     * 添加一个换肤 style
     *
     * @param attr
     * @param attrName
     * @param attrElementClazz
     *
     */
    fun registerSkinStyle(attrName: String, attr: Int, attrElementClazz: Class<out SkinElementAttr>) : SkinElementAttrFactory{
        mSkinStyle[attr] = mapOf(attrName to attrElementClazz)
        return this
    }

    init {
        registerSkinAttr("background", BackgroundAttr::class.java)
        registerSkinStyle("background", R.attr.background, BackgroundAttr::class.java)
        registerSkinAttr("src", ImageSrcAttr::class.java)
        registerSkinStyle("background", android.R.attr.src, BackgroundAttr::class.java)
        registerSkinAttr("srcCompat", ImageSrcAttr::class.java)
        registerSkinStyle("background", R.attr.srcCompat, BackgroundAttr::class.java)

        // TextView drawable
        registerSkinAttr("textColor", TextColorAttr::class.java)
        registerSkinStyle("textColor", android.R.attr.textColor, TextColorAttr::class.java)
        registerSkinAttr("textColorHint", TextColorAttr::class.java)
        registerSkinStyle("textColorHint", android.R.attr.textColorHint, TextColorAttr::class.java)

        registerSkinAttr("drawableLeft", TextViewDrawableAttr::class.java)
        registerSkinStyle("drawableLeft", android.R.attr.drawableLeft, TextViewDrawableAttr::class.java)
        registerSkinAttr("drawableTop", TextViewDrawableAttr::class.java)
        registerSkinStyle("drawableTop", android.R.attr.drawableTop, TextViewDrawableAttr::class.java)
        registerSkinAttr("drawableRight", TextViewDrawableAttr::class.java)
        registerSkinStyle("drawableRight", android.R.attr.drawableRight, TextViewDrawableAttr::class.java)
        registerSkinAttr("drawableBottom", TextViewDrawableAttr::class.java)
        registerSkinStyle("drawableBottom", android.R.attr.drawableBottom, TextViewDrawableAttr::class.java)
    }

    fun registerTextSizeSkinAttr() : SkinElementAttrFactory{
        registerSkinAttr("textSize", TextSizeAttr::class.java)
        registerSkinStyle("textSize", android.R.attr.textSize, TextSizeAttr::class.java)
        return this
    }

    fun registerPaddingSkinAttr() : SkinElementAttrFactory{
        // padding
        registerSkinAttr("padding", PaddingAttr::class.java)
        registerSkinAttr("paddingLeft", PaddingAttr::class.java)
        registerSkinAttr("paddingStart", PaddingAttr::class.java)
        registerSkinAttr("paddingRight", PaddingAttr::class.java)
        registerSkinAttr("paddingEnd", PaddingAttr::class.java)
        registerSkinAttr("paddingTop", PaddingAttr::class.java)
        registerSkinAttr("paddingBottom", PaddingAttr::class.java)

        registerSkinStyle("padding", android.R.attr.padding, PaddingAttr::class.java)
        registerSkinStyle("paddingLeft", android.R.attr.paddingLeft, PaddingAttr::class.java)
        registerSkinStyle("paddingStart", android.R.attr.paddingStart, PaddingAttr::class.java)
        registerSkinStyle("paddingRight", android.R.attr.paddingRight, PaddingAttr::class.java)
        registerSkinStyle("paddingEnd", android.R.attr.paddingEnd, PaddingAttr::class.java)
        registerSkinStyle("paddingTop", android.R.attr.paddingTop, PaddingAttr::class.java)
        registerSkinStyle("paddingBottom", android.R.attr.paddingBottom, PaddingAttr::class.java)
        return this
    }

    fun registerMarginSkinAttr() : SkinElementAttrFactory{
        // margin
        registerSkinAttr("layout_margin", MarginAttr::class.java)
        registerSkinAttr("layout_marginLeft", MarginAttr::class.java)
        registerSkinAttr("layout_marginStart", MarginAttr::class.java)
        registerSkinAttr("layout_marginRight", MarginAttr::class.java)
        registerSkinAttr("layout_marginEnd", MarginAttr::class.java)
        registerSkinAttr("layout_marginTop", MarginAttr::class.java)
        registerSkinAttr("layout_marginBottom", MarginAttr::class.java)

        registerSkinStyle("layout_margin", android.R.attr.layout_margin, MarginAttr::class.java)
        registerSkinStyle("layout_marginLeft", android.R.attr.layout_marginLeft, MarginAttr::class.java)
        registerSkinStyle("layout_marginStart", android.R.attr.layout_marginStart, MarginAttr::class.java)
        registerSkinStyle("layout_marginRight", android.R.attr.layout_marginRight, MarginAttr::class.java)
        registerSkinStyle("layout_marginEnd", android.R.attr.layout_marginEnd, MarginAttr::class.java)
        registerSkinStyle("layout_marginTop", android.R.attr.layout_marginTop, MarginAttr::class.java)
        registerSkinStyle("layout_marginBottom", android.R.attr.layout_marginBottom, MarginAttr::class.java)
        return this
    }

    /**
     *  创建换肤节点
     */
    fun createSkinAttr(attrName: String, attrValueRefId: Int, attrValueRefName: String?, typeName: String?): SkinElementAttr? {
        val skinAttr = supportSkinAttrs[attrName]?.newInstance()
        skinAttr?.run {
            this.attrName = attrName
            this.attrValueRefId = attrValueRefId
            this.attrValueRefName = attrValueRefName
            this.attrValueTypeName = typeName
        }
        return skinAttr
    }

    fun isSupportedAttr(attrName: String): Boolean {
        return supportSkinAttrs.keys.contains(attrName)
    }

    fun isSupportedAttr(attr: Int): Boolean {
        return mSkinStyle.keys.contains(attr)
    }

    fun getSkinStyle(): IntArray{
        return mSkinStyle.keys.toIntArray()
    }
}
