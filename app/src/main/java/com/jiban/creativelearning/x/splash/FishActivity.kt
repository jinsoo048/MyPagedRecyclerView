package com.jiban.creativelearning.x.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.jiban.creativelearning.x.config.BaseActivity
import com.jiban.creativelearning.MainActivity
import com.jiban.creativelearning.databinding.ActivityFishBinding


class FishActivity : BaseActivity<ActivityFishBinding>(ActivityFishBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 1500)
    }
}