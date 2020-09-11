package com.kingsley.simple

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kingsley.skin.SkinFactory2
import com.kingsley.skin.SkinManager
import com.kingsley.skin.util.L
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        tv_hello.setOnClickListener {
            if (!it.isSelected) {
                it.isSelected = true
                SkinManager.applySkinName(
                    "dark",
                    changeCallback = object : SkinManager.ILoaderListener {
                        override fun onSuccess() {
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                                window.statusBarColor = Color.GREEN
                            }
                        }
                    })
            } else {
                it.isSelected = false
                SkinManager.restoreDefaultTheme(changeCallback = object :
                    SkinManager.ILoaderListener {
                    override fun onSuccess() {
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                            window.statusBarColor = Color.RED
                        }
                    }
                })
            }
        }

        L.d(SkinFactory2.TAG,"onCreateView LineStyle : ${R.style.LineStyle}")
        L.d(SkinFactory2.TAG,"onCreateView LineStyle 2 : ${R.color.line_color}")
    }
}
