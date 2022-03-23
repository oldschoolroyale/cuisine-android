package com.brm.coosin.ui.config

import android.content.Intent
import android.os.Bundle
import android.view.Display
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.brm.coosin.data.network.ApiServiceImpl
import com.brm.coosin.databinding.ActivitySigninBinding
import com.brm.coosin.ui.main.MainActivity
import com.brm.coosin.util.AppPreferences
import io.reactivex.disposables.Disposable

class SigninActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySigninBinding
    private var disposable: Disposable? = null
    val apiService = ApiServiceImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signupBtn.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }

        binding.signinBtn.setOnClickListener {
            binding.progressPb.visibility = View.VISIBLE
            val username = binding.loginEdt.text.toString()
            val password = binding.passwordEdt.text.toString()
            disposable = apiService.signIn(username, password)
                .subscribe({
                    binding.progressPb.visibility = View.INVISIBLE
                    AppPreferences.token = it
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }, {
                    binding.progressPb.visibility = View.INVISIBLE
                    Toast.makeText(this, "Error, try again", Toast.LENGTH_LONG).show()
                })
        }

        binding.forgotPasswordTv.setOnClickListener {
            //TODO("FORGOT PASSWORD ACTIVITY")
        }
    }

    override fun onDestroy() {
        disposable?.dispose()
        super.onDestroy()
    }
}