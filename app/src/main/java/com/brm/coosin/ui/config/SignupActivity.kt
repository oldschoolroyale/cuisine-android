package com.brm.coosin.ui.config

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.brm.coosin.data.network.ApiServiceImpl
import com.brm.coosin.databinding.ActivitySignupBinding
import com.brm.coosin.model.Lead
import com.brm.coosin.ui.main.MainActivity
import com.brm.coosin.util.AppPreferences
import io.reactivex.disposables.Disposable

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private val apiService = ApiServiceImpl()
    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.haveAccount.setOnClickListener {
            finish()
        }

        binding.signUpBtn.setOnClickListener {
            binding.progressPb.visibility = View.VISIBLE

            val name = binding.nameEdt.text.toString()
            val password = binding.passwordEdt.text.toString()
            val repeatPassword = binding.passwordRepEdt.text.toString()
            val username = binding.usernameEdt.text.toString()
            val email = binding.emailEdt.text.toString()

            if (valid(name, password, repeatPassword, email)) {
                //Интернет запрос, если все ок
                val lead = Lead(
                    name = name,
                    password = password,
                    username = username,
                    email = email
                )

                disposable = apiService
                    .register(lead)
                    .subscribe({
                        // тут мы получили ответ String (token)
                        //Если получили ответ, то его сохраняем в AppPreferences и идем в MainActivity
                        binding.progressPb.visibility = View.INVISIBLE
                        AppPreferences.token = it
                        startActivity(Intent(this, MainActivity::class.java))
                    }, {
                        //тут вышла ошибка
                        //Тут выводим ошибку и пробуем заново
                        //ну типо если сервер выключен или не отвечает
                        binding.progressPb.visibility = View.INVISIBLE
                        Toast.makeText(this, "Error, try again", Toast.LENGTH_LONG).show()
                    })

            }

        }

    }

    private fun valid(
        name: String,
        password: String,
        repeatPassword: String,
        email: String
    ): Boolean {
        if (!name.matches(Regex("[a-zA-Zа-яА-Я]+\$")) ||
            !email.matches(Regex("[a-zA-Z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}")) ||
            password != repeatPassword
        ) {
            binding.progressPb.visibility = View.INVISIBLE
            Toast.makeText(this, "Error, incorrect field", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

    override fun onDestroy() {
        //Если сделан интернет запрос, но пользователь вышел из приложения в это время,
        // приложение может крашнуться
        //Чтобы этого не произошло, мы выключаем запрос во время выхода
        //Теперь нужно дать разрешение на интернет
        disposable?.dispose()
        super.onDestroy()
    }
}