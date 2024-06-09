package com.capstone.knowy.ui.test

import androidx.lifecycle.ViewModel
import com.capstone.knowy.data.repository.Repository

class TestViewModel(private val repository: Repository) : ViewModel() {
    fun getDetail() = repository.getUserDetail()
}