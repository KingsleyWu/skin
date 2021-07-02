package com.kingsley.simple

import android.graphics.Color
import android.os.Bundle
import com.kingsley.simple.databinding.ActivityMainBinding
import com.kingsley.skin.listener.ILoaderListener
import com.kingsley.skin.SkinManager
import com.kingsley.skin.util.L

/**
 * @author Kingsley
 * Created on 2021/7/2.
 */
class SecActivity: BaseSkinActivity() {

    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            val currentSkinName = SkinManager.currentSkinName()
            if (currentSkinName == "dark") {
                binding.tvHello.isSelected = true
                window.statusBarColor = Color.GREEN
            } else {
                binding.tvHello.isSelected = false
                window.statusBarColor = Color.RED
            }
        }

        binding.tvHello.setOnClickListener {
            if (!it.isSelected) {
                it.isSelected = true
                SkinManager.applySkinName(
                    "dark",
                    object : ILoaderListener {
                        override fun onSuccess() {
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                                window.statusBarColor = Color.GREEN
                            }
                        }
                    })
            } else {
                it.isSelected = false
                SkinManager.restoreDefaultTheme(object : ILoaderListener {
                    override fun onSuccess() {
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                            window.statusBarColor = Color.RED
                        }
                    }
                })
            }
        }
        L.d(TAG,"onCreateView LineStyle : ${R.style.LineStyle}")
        L.d(TAG,"onCreateView LineStyle 2 : ${R.color.main_background}")
    }
}