package com.capstone.knowy.ui.forum.newdiscussion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.capstone.knowy.databinding.FragmentNewDiscussionBinding

class NewDiscussionFragment : Fragment() {

    private var _binding: FragmentNewDiscussionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewDiscussionBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }
}