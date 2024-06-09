package com.capstone.knowy.ui.test.ocean.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.capstone.knowy.R
import com.capstone.knowy.databinding.ActivityOceanHomeBinding
import com.capstone.knowy.ui.test.ocean.testview.OceanTestActivity

class OceanHomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOceanHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOceanHomeBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.ocean_home_activity)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnOpenness.setOnClickListener() {
            val intent = Intent(this@OceanHomeActivity, OceanTestActivity::class.java)
            intent.putExtra(OceanTestActivity.EXTRA_TEST_NAME, "Openness Test")
            startActivity(intent)
        }
        binding.btnConscientiousness.setOnClickListener() {
            val intent = Intent(this@OceanHomeActivity, OceanTestActivity::class.java)
            intent.putExtra(OceanTestActivity.EXTRA_TEST_NAME, "Conscientiousness Test")
            startActivity(intent)
        }
        binding.btnExtraVersion.setOnClickListener() {
            val intent = Intent(this@OceanHomeActivity, OceanTestActivity::class.java)
            intent.putExtra(OceanTestActivity.EXTRA_TEST_NAME, "Extraversion Test")
            startActivity(intent)
        }
        binding.btnAgreeableness.setOnClickListener() {
            val intent = Intent(this@OceanHomeActivity, OceanTestActivity::class.java)
            intent.putExtra(OceanTestActivity.EXTRA_TEST_NAME, "Agreeableness Test")
            startActivity(intent)
        }
        binding.btnNeuroticism.setOnClickListener() {
            val intent = Intent(this@OceanHomeActivity, OceanTestActivity::class.java)
            intent.putExtra(OceanTestActivity.EXTRA_TEST_NAME, "Neuroticism Test")
            startActivity(intent)
        }
    }
}