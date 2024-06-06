package com.capstone.knowy.ui.test.aptitude.testview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.capstone.knowy.R
import com.capstone.knowy.databinding.ActivityAptitudeTestBinding
import com.capstone.knowy.ui.test.ocean.testview.OceanTestActivity

class AptitudeTestActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAptitudeTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAptitudeTestBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.aptitude_test_activity)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        when (val testName = intent.getStringExtra(OceanTestActivity.EXTRA_TEST_NAME)) {
            getString(R.string.numerical_aptitude) -> {
                binding.topAppBar.title = testName
            }
            getString(R.string.spatial_aptitude) -> {
                binding.topAppBar.title = testName
            }
            getString(R.string.perceptual_aptitude) -> {
                binding.topAppBar.title = testName
            }
            getString(R.string.abstract_reasoning) -> {
                binding.topAppBar.title = testName
            }
            else -> {
                binding.topAppBar.title = testName
            }
        }


    }

    companion object{
        const val TEST_NAME = "extra_text_name"
    }
}