package com.kingsley.simple

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import com.kingsley.simple.databinding.ActivityMainBinding
import com.kingsley.skin.SkinManager
import com.kingsley.skin.util.L

class MainActivity : BaseSkinActivity() {

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
                window.statusBarColor = Color.GREEN
            } else {
                window.statusBarColor = Color.RED
            }
        }

        binding.tvHello.setOnClickListener {
            startActivity(Intent(this, SecActivity::class.java))
        }
        L.d(TAG,"onCreateView LineStyle : ${R.style.LineStyle}")
        L.d(TAG,"onCreateView LineStyle 2 : ${R.color.main_background}")
    }

    override fun onSkinChanged(){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            val currentSkinName = SkinManager.currentSkinName()
            if (currentSkinName == "dark") {
                window.statusBarColor = Color.GREEN
            } else {
                window.statusBarColor = Color.RED
            }
        }
    }
}
