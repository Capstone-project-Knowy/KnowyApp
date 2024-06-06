package com.capstone.knowy.ui.forum.discussion

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.capstone.knowy.R
import com.capstone.knowy.databinding.FragmentForumDiscussionBinding
import com.capstone.knowy.ui.forum.upload.UploadDiscussionActivity

class ForumDiscussionFragment : Fragment() {
    private var _binding: FragmentForumDiscussionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForumDiscussionBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.btnCreate.setOnClickListener() {
            val intent = Intent(activity, UploadDiscussionActivity::class.java)
            startActivity(intent)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val USERNAME = "username"
        private val TAB_TITLES = intArrayOf(R.string.trending_discussion, R.string.new_discussion)
    }

}