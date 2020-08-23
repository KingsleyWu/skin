package com.androidx.appcompat.app

import androidx.appcompat.R
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewParent
import androidx.appcompat.view.ContextThemeWrapper
import androidx.appcompat.widget.TintContextWrapper
import androidx.appcompat.widget.VectorEnabledTintResources
import androidx.core.view.ViewCompat
import org.xmlpull.v1.XmlPullParser

object ContextUtils {

    @SuppressLint("RestrictedApi")
    fun wrapContext(context: Context, parent: View?, attrs: AttributeSet): Context {
        var context: Context = context
        val isPre21: Boolean = Build.VERSION.SDK_INT < 21

        // We only want the View to inherit its context if we're running pre-v21
        val inheritContext: Boolean =
            if (isPre21 && attrs is XmlPullParser // If we have a XmlPullParser, we can detect where we are in the layout
            ) (attrs as XmlPullParser).depth > 1 // Otherwise we have to use the old heuristic
            else shouldInheritContext(context, parent as ViewParent?)
        val readAppTheme = true /* Read read app:theme as a fallback at all times for legacy reasons */
        val wrapContext = VectorEnabledTintResources.shouldBeUsed() /* Only tint wrap the context if enabled */

        // We can emulate Lollipop's android:theme attribute propagating down the view hierarchy
        // by using the parent's context
        if (inheritContext && parent != null) {
            context = parent.context
        }
        if (isPre21 || readAppTheme) {
            // We then apply the theme on the context, if specified
            context = themifyContext(context, attrs, isPre21, readAppTheme)
        }
        if (wrapContext) {
            context = TintContextWrapper.wrap(context)
        }
        return context
    }

    private fun shouldInheritContext(context: Context?, parent: ViewParent?): Boolean {
        var parent: ViewParent? = parent
            ?: // The initial parent is null so just return false
            return false
        if (context is Activity) {
            val windowDecor = context.window.decorView
            while (true) {
                if (parent == null) {
                    // Bingo. We've hit a view which has a null parent before being terminated from
                    // the loop. This is (most probably) because it's the root view in an inflation
                    // call, therefore we should inherit. This works as the inflated layout is only
                    // added to the hierarchy at the end of the inflate() call.
                    return true
                } else if (parent === windowDecor || parent !is View
                    || ViewCompat.isAttachedToWindow(parent)
                ) {
                    // We have either hit the window's decor view, a parent which isn't a View
                    // (i.e. ViewRootImpl), or an attached view, so we know that the original parent
                    // is currently added to the view hierarchy. This means that it has not be
                    // inflated in the current inflate() call and we should not inherit the context.
                    return false
                }
                parent = parent.getParent()
            }
        }
        return false
    }

    /**
     * Allows us to emulate the `android:theme` attribute for devices before L.
     */
    private fun themifyContext(
        context: Context, attrs: AttributeSet,
        useAndroidTheme: Boolean, useAppTheme: Boolean
    ): Context {
        var context = context
        val a = context.obtainStyledAttributes(attrs, R.styleable.View, 0, 0)
        var themeId = 0
        if (useAndroidTheme) {
            // First try reading android:theme if enabled
            themeId = a.getResourceId(R.styleable.View_android_theme, 0)
        }
        if (useAppTheme && themeId == 0) {
            // ...if that didn't work, try reading app:theme (for legacy reasons) if enabled
            themeId = a.getResourceId(R.styleable.View_theme, 0)
            if (themeId != 0) {
                Log.i("ContextUtils", "app:theme is now deprecated. "
                            + "Please move to using android:theme instead.")
            }
        }
        a.recycle()
        if (themeId != 0 && (context !is ContextThemeWrapper || context.themeResId !== themeId)) {
            // If the context isn't a ContextThemeWrapper, or it is but does not have
            // the same theme as we need, wrap it in a new wrapper
            context = ContextThemeWrapper(context, themeId)
        }
        return context
    }
}