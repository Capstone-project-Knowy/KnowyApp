package com.capstone.knowy.ui.test

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.capstone.knowy.data.di.Injection
import com.capstone.knowy.data.response.User
import com.capstone.knowy.data.result.Result
import com.capstone.knowy.databinding.FragmentTestBinding
import com.capstone.knowy.ui.factory.ViewModelFactory
import com.capstone.knowy.ui.test.aptitude.home.AptitudeHomeActivity
import com.capstone.knowy.ui.test.ocean.home.OceanHomeActivity
import com.capstone.knowy.ui.test.result.ResultTestActivity

class TestFragment : Fragment() {
    private var _binding: FragmentTestBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TestViewModel by viewModels {
        ViewModelFactory.useViewModelFactory {
            TestViewModel(Injection.provideRepository(requireActivity()))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel.getDetail().observe(viewLifecycleOwner) {
            getUsername(it)
        }

        _binding = FragmentTestBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.btnGoToOcean.setOnClickListener() {
            val intent = Intent(activity, OceanHomeActivity::class.java)
            startActivity(intent)
        }

        binding.btnGoToAptitude.setOnClickListener() {
            val intent = Intent(activity, AptitudeHomeActivity::class.java)
            startActivity(intent)
        }

        binding.btnAnalyze.setOnClickListener() {
            val intent = Intent(activity, ResultTestActivity::class.java)
            startActivity(intent)
        }
        return root
    }

    private fun getUsername(result: Result<User>) {
        showLoading(result is Result.Loading)
        when (result) {
            is Result.Success -> {
                val user = result.data
                binding.tvUsername.text = user.username
                Log.d("Result Data", user.username)
            }

            else -> {}
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressIndicator.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}