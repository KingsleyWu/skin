package com.kingsley.simple

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.kingsley.skin.SkinManager
import kotlinx.android.synthetic.main.activity_main.*
import skin.support.SkinCompatManager


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_hello.setOnClickListener(View.OnClickListener {
            if (!it.isSelected) {
                tv_hello.isSelected = true
                SkinManager.applySkinName("dark")
                //setTheme(R.style.AppTheme)
            } else {
                tv_hello.isSelected = false
                SkinManager.restoreDefaultTheme()
            }
        })
//        AppCompatResources.
    }
}
