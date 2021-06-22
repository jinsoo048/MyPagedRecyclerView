package com.example.mypagedrecyclerview.x.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.mypagedrecyclerview.x.config.BaseActivity
import com.example.mypagedrecyclerview.MainActivity
import com.example.mypagedrecyclerview.databinding.ActivitySplashBinding


class FishActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 1500)
    }
}