package com.kingsley.skin

import com.kingsley.skin.attrs.*

/**
 * 在这里可以添加动态换肤的属性
 *
 */
object SkinElementAttrFactory {

    private val supportSkinAttrs: MutableMap<String, Class<out SkinElementAttr>> = mutableMapOf()

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

    init {
        registerSkinAttr("background", BackgroundAttr::class.java)
        registerSkinAttr("src", ImageSrcAttr::class.java)

        // TextView drawable
        registerSkinAttr("textColor", TextColorAttr::class.java)
        registerSkinAttr("drawableLeft", TextViewDrawableAttr::class.java)
        registerSkinAttr("drawableTop", TextViewDrawableAttr::class.java)
        registerSkinAttr("drawableRight", TextViewDrawableAttr::class.java)
        registerSkinAttr("drawableBottom", TextViewDrawableAttr::class.java)
    }

    fun registerTextSizeSkinAttr() : SkinElementAttrFactory{
        registerSkinAttr("textSize", TextSizeAttr::class.java)
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
}
