package com.capstone.knowy.ui.profile.edit

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.capstone.knowy.R
import com.capstone.knowy.databinding.ActivityEditProfileBinding
import com.capstone.knowy.ui.profile.detail.ProfileFragment

class EditProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnSaveProfile.setOnClickListener(){
            loadFragment(ProfileFragment())
        }

        playAnimation()
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.forum_discussion_fragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun playAnimation() {
        val img = ObjectAnimator.ofFloat(binding.civAvatar, View.ALPHA, 1f).setDuration(500)
        val fullName = ObjectAnimator.ofFloat(binding.tvFullName, View.ALPHA, 1f).setDuration(500)
        val tilFulName =
            ObjectAnimator.ofFloat(binding.tilFullName, View.ALPHA, 1f).setDuration(500)
        val username = ObjectAnimator.ofFloat(binding.tvUsername, View.ALPHA, 1f).setDuration(500)
        val tilUsername =
            ObjectAnimator.ofFloat(binding.tilUsername, View.ALPHA, 1f).setDuration(500)
        val age = ObjectAnimator.ofFloat(binding.tvAge, View.ALPHA, 1f).setDuration(500)
        val tilAge = ObjectAnimator.ofFloat(binding.tilAge, View.ALPHA, 1f).setDuration(500)
        val gender = ObjectAnimator.ofFloat(binding.tvGender, View.ALPHA, 1f).setDuration(500)
        val tilGender = ObjectAnimator.ofFloat(binding.tilGender, View.ALPHA, 1f).setDuration(500)
        val bio = ObjectAnimator.ofFloat(binding.tvDescBio, View.ALPHA, 1f).setDuration(500)
        val tilBio = ObjectAnimator.ofFloat(binding.tilDescBio, View.ALPHA, 1f).setDuration(500)
        val btnSave = ObjectAnimator.ofFloat(binding.btnSaveProfile, View.ALPHA, 1f).setDuration(500)

        AnimatorSet().apply {
            playSequentially(img,fullName, tilFulName, username, tilUsername, age, tilAge, gender, tilGender, bio, tilBio, btnSave)
        }.start()
    }
}