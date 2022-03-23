package com.brm.coosin.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.brm.coosin.R
import com.brm.coosin.ui.config.SigninActivity
import com.brm.coosin.ui.main.MainActivity
import com.brm.coosin.util.AppPreferences

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val token = AppPreferences.token

        if (token == null) {
            startActivity(Intent(this, SigninActivity::class.java))
            finish()
        }
        else{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}