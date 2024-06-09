package com.capstone.knowy.ui.forum.upload

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
import com.capstone.knowy.databinding.ActivityUploadDiscussionBinding
import com.capstone.knowy.ui.factory.ViewModelFactory
import com.capstone.knowy.ui.forum.discussion.ForumDiscussionFragment

class UploadDiscussionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUploadDiscussionBinding

    private val viewModel: UploadDiscussionViewModel by viewModels {
        ViewModelFactory.useViewModelFactory {
            UploadDiscussionViewModel(Injection.provideRepository(this))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadDiscussionBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.upload_activity)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        binding.btnUpload.setOnClickListener() {
            createDiscussion()
        }

        playAnimation()
    }

    private fun createDiscussion() {
        viewModel.createDiscussion(
            binding.etHeadTopic.text.toString(),
            binding.etDescription.text.toString()
        ).observe(this) {
            if (it is Result.Loading) {
                showLoading(true)
            } else {
                showLoading(false)
                when (it) {
                    is Result.Success -> {
                        loadFragment(ForumDiscussionFragment())
                    }

                    is Result.Error -> {
                        Toast.makeText(
                            this,
                            "Failed Create Discussion : ${it.error})",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    else -> {}
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressIndicator.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.forum_discussion_fragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun playAnimation() {
        val title = ObjectAnimator.ofFloat(binding.tvTitleUploadDiscussion, View.ALPHA, 1f)
            .setDuration(1000)
        val img =
            ObjectAnimator.ofFloat(binding.ivUploadDisccussion, View.ALPHA, 1f).setDuration(500)
        val headTopic =
            ObjectAnimator.ofFloat(binding.tvHeadTopic, View.ALPHA, 1f).setDuration(500)
        val tilHeadTopic =
            ObjectAnimator.ofFloat(binding.tilHeadTopic, View.ALPHA, 1f).setDuration(500)
        val description =
            ObjectAnimator.ofFloat(binding.tvDescription, View.ALPHA, 1f).setDuration(500)
        val tilDescription =
            ObjectAnimator.ofFloat(binding.tilDescription, View.ALPHA, 1f).setDuration(500)
        val btnUpload = ObjectAnimator.ofFloat(binding.btnUpload, View.ALPHA, 1f).setDuration(500)

        AnimatorSet().apply {
            playSequentially(
                title, img, headTopic, tilHeadTopic, description, tilDescription, btnUpload
            )
        }.start()
    }


}