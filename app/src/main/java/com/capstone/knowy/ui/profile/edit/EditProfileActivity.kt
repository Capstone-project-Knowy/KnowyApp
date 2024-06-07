package com.capstone.knowy.ui.profile.edit

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.capstone.knowy.R
import com.capstone.knowy.data.di.Injection
import com.capstone.knowy.data.result.Result
import com.capstone.knowy.databinding.ActivityEditProfileBinding
import com.capstone.knowy.ui.factory.ViewModelFactory
import com.capstone.knowy.ui.profile.detail.ProfileFragment

class EditProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditProfileBinding

    private val viewModel: EditProfileViewModel by viewModels {
        ViewModelFactory.useViewModelFactory {
            EditProfileViewModel(Injection.provideRepository(this))
        }
    }

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

        binding.btnSaveProfile.setOnClickListener() {
            editProfile()
        }

        playAnimation()
    }

    private fun editProfile() {
        viewModel.editProfileUser(
            binding.etFullName.text.toString(),
            binding.etUsername.text.toString()
        ).observe(this) {
            if (it is Result.Loading){
                showLoading(false)
            }
            else{
                showLoading(false)
                when(it){
                    is Result.Success -> {
                        Toast.makeText(this, "Profile has been Updated", Toast.LENGTH_SHORT).show()
                        loadFragment(ProfileFragment())
                    }
                    is Result.Error -> {
                        Toast.makeText(this, "Edit Failed : ${it.error})", Toast.LENGTH_SHORT).show()
                    }
                    else -> {}
                }
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.forum_discussion_fragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun playAnimation() {
        val img = ObjectAnimator.ofFloat(binding.civAvatar, View.ALPHA, 1f).setDuration(350)
        val fullName = ObjectAnimator.ofFloat(binding.tvFullName, View.ALPHA, 1f).setDuration(350)
        val tilFulName =
            ObjectAnimator.ofFloat(binding.tilFullName, View.ALPHA, 1f).setDuration(350)
        val username = ObjectAnimator.ofFloat(binding.tvUsername, View.ALPHA, 1f).setDuration(350)
        val tilUsername =
            ObjectAnimator.ofFloat(binding.tilUsername, View.ALPHA, 1f).setDuration(350)
        val btnSave =
            ObjectAnimator.ofFloat(binding.btnSaveProfile, View.ALPHA, 1f).setDuration(350)

        AnimatorSet().apply {
            playSequentially(img, fullName, tilFulName, username, tilUsername, btnSave)
        }.start()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressIndicator.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}