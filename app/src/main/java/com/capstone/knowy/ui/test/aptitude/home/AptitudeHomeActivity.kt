package com.capstone.knowy.ui.test.aptitude.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.capstone.knowy.R
import com.capstone.knowy.databinding.ActivityAptitudeHomeBinding
import com.capstone.knowy.ui.test.aptitude.testview.AptitudeTestActivity
import com.capstone.knowy.ui.test.ocean.testview.OceanTestActivity

class AptitudeHomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAptitudeHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAptitudeHomeBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.aptitude_home_activity)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnNumerical.setOnClickListener() {
            val intent = Intent(this@AptitudeHomeActivity, OceanTestActivity::class.java)
            intent.putExtra(AptitudeTestActivity.TEST_NAME, "Numerical Aptitude Test")
            startActivity(intent)
        }
        binding.btnSpatial.setOnClickListener() {
            val intent = Intent(this@AptitudeHomeActivity, OceanTestActivity::class.java)
            intent.putExtra(AptitudeTestActivity.TEST_NAME, "Spatial Aptitude Test")
            startActivity(intent)
        }
        binding.btnPerceptual.setOnClickListener() {
            val intent = Intent(this@AptitudeHomeActivity, OceanTestActivity::class.java)
            intent.putExtra(AptitudeTestActivity.TEST_NAME, "Perceptual Aptitude Test")
            startActivity(intent)
        }
        binding.btnAbstrac.setOnClickListener() {
            val intent = Intent(this@AptitudeHomeActivity, OceanTestActivity::class.java)
            intent.putExtra(AptitudeTestActivity.TEST_NAME, "Abstract Reasoning Test")
            startActivity(intent)
        }
        binding.btnVerbal.setOnClickListener() {
            val intent = Intent(this@AptitudeHomeActivity, OceanTestActivity::class.java)
            intent.putExtra(AptitudeTestActivity.TEST_NAME, "Verbal Reasoning Test")
            startActivity(intent)
        }
    }
}