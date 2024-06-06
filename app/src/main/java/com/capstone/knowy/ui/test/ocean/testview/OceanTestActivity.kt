package com.capstone.knowy.ui.test.ocean.testview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.capstone.knowy.R
import com.capstone.knowy.databinding.ActivityOceanTestBinding

class OceanTestActivity : AppCompatActivity() {

    private lateinit var binding : ActivityOceanTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOceanTestBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.ocean_test_activity)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        when (val testName = intent.getStringExtra(EXTRA_TEST_NAME)) {
            getString(R.string.openness) -> {
                binding.topAppBar.title = testName
            }
            getString(R.string.conscientiousness) -> {
                binding.topAppBar.title = testName
            }
            getString(R.string.extra_version) -> {
                binding.topAppBar.title = testName
            }
            getString(R.string.agreeableness) -> {
                binding.topAppBar.title = testName
            }
            else -> {
                binding.topAppBar.title = testName
            }
        }
    }

    companion object{
        const val EXTRA_TEST_NAME = "extra_text_name"
    }
}