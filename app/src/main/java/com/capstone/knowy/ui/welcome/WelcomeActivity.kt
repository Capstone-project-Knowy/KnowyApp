package com.capstone.knowy.ui.welcome

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.capstone.knowy.R
import com.capstone.knowy.data.di.Injection
import com.capstone.knowy.databinding.ActivityWelcomeBinding
import com.capstone.knowy.ui.factory.ViewModelFactory
import com.capstone.knowy.ui.home.MainActivity
import com.capstone.knowy.ui.login.LoginActivity

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding

    private val viewModel: WelcomeViewModel by viewModels {
        ViewModelFactory.useViewModelFactory {
            WelcomeViewModel(Injection.provideRepository(this))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.welcome_activity)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnStarted.setOnClickListener() {
            val intent = Intent(this@WelcomeActivity, LoginActivity::class.java)
            startActivity(intent)
        }

        playAnimation()

        viewModel.getSession().observe(this) { user ->
            if (user.isLogin) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }

    private fun playAnimation() {
        val title = ObjectAnimator.ofFloat(binding.tvTitle, View.ALPHA, 1f).setDuration(500)
        val motto = ObjectAnimator.ofFloat(binding.tvAppMotto, View.ALPHA, 1f).setDuration(500)
        val btnStart = ObjectAnimator.ofFloat(binding.btnStarted, View.ALPHA, 1f).setDuration(500)

        AnimatorSet().apply {
            playSequentially(
                title, motto, btnStart
            )
            startDelay = 500
        }.start()
    }
}