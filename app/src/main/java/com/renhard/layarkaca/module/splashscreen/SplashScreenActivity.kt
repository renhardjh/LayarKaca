package com.renhard.layarkaca.module.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.renhard.layarkaca.databinding.ActivitySplashscreenBinding
import com.renhard.layarkaca.module.main.MainActivity

class SplashScreenActivity: AppCompatActivity() {
    val time = 2000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activitySplashBinding = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(activitySplashBinding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            startMainActivity()
            finish()
        }, time)
    }

    private fun startMainActivity() {
        val mainAct = Intent(this, MainActivity::class.java)
        startActivity(mainAct)
    }
}