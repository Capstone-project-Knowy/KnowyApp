package com.capstone.knowy.ui.test.result

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
import com.capstone.knowy.data.response.User
import com.capstone.knowy.data.result.Result
import com.capstone.knowy.databinding.ActivityResultTestBinding
import com.capstone.knowy.ui.factory.ViewModelFactory
import com.capstone.knowy.ui.home.MainActivity

class ResultTestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultTestBinding

    private val viewModel: ResultViewModel by viewModels {
        ViewModelFactory.useViewModelFactory {
            ResultViewModel(Injection.provideRepository(this))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultTestBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.result_test_activity)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel.getUserDetail().observe(this) {
            getUserDetail(it)
        }

        binding.btnBackToHome.setOnClickListener() {
            val intent = Intent(this@ResultTestActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getUserDetail(result: Result<User>) {
        showLoading(result is Result.Loading)
        when (result) {
            is Result.Success -> {
                val user = result.data
                binding.tvUsername.text = user.username
                binding.tvFullName.text = user.fullname
            }

            else -> {}
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressIndicator.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}