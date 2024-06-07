package com.capstone.knowy.ui.register

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.capstone.knowy.R
import com.capstone.knowy.data.di.Injection
import com.capstone.knowy.data.result.Result
import com.capstone.knowy.databinding.ActivityRegisterBinding
import com.capstone.knowy.ui.factory.ViewModelFactory
import com.capstone.knowy.ui.login.LoginActivity

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    private val viewModel: RegisterViewModel by viewModels {
        ViewModelFactory.useViewModelFactory {
            RegisterViewModel(Injection.provideRepository(this))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.register_activity)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnRegister.setOnClickListener() {
            register()
        }

        playAnimation()
    }

    private fun register() {
        viewModel.registerUser(
            binding.etRegisterEmail.text.toString(),
            binding.etRegisterUsername.text.toString(),
            binding.etRegisterPassword.text.toString(),
            binding.etRegisterConfirmPassword.text.toString()
        ).observe(this) {
            if (it is Result.Loading){
                showLoading(true)
            }
            else {
                showLoading(false)
                when(it) {
                    is Result.Success -> {
                        Toast.makeText(this, "Register Success", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                        startActivity(intent)
                    }

                    is Result.Error -> {
                        Toast.makeText(this, "Register Failed : ${it.error})", Toast.LENGTH_SHORT).show()
                    }
                    else -> {}
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressIndicator.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.imgRegister, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val register =
            ObjectAnimator.ofFloat(binding.tvRegisterAccount, View.ALPHA, 1f).setDuration(350)
        val personal =
            ObjectAnimator.ofFloat(binding.tvPersonalInformation, View.ALPHA, 1f).setDuration(350)
        val username = ObjectAnimator.ofFloat(binding.tvUsername, View.ALPHA, 1f).setDuration(350)
        val usernameTil =
            ObjectAnimator.ofFloat(binding.tilUsername, View.ALPHA, 1f).setDuration(350)
        val email = ObjectAnimator.ofFloat(binding.tvEmail, View.ALPHA, 1f).setDuration(350)
        val emailTil = ObjectAnimator.ofFloat(binding.tilEmail, View.ALPHA, 1f).setDuration(350)
        val password = ObjectAnimator.ofFloat(binding.tvPassword, View.ALPHA, 1f).setDuration(350)
        val passwordTil =
            ObjectAnimator.ofFloat(binding.tilPassword, View.ALPHA, 1f).setDuration(350)
        val confirmPassword =
            ObjectAnimator.ofFloat(binding.tvConfirmPassword, View.ALPHA, 1f).setDuration(350)
        val confirmPasswordTil =
            ObjectAnimator.ofFloat(binding.tilConfirmPassword, View.ALPHA, 1f).setDuration(350)
        val btnRegister =
            ObjectAnimator.ofFloat(binding.btnRegister, View.ALPHA, 1f).setDuration(350)

        AnimatorSet().apply {
            playSequentially(
                register,
                personal,
                username,
                usernameTil,
                email,
                emailTil,
                password,
                passwordTil,
                confirmPassword,
                confirmPasswordTil,
                btnRegister
            )
        }.start()

    }
}