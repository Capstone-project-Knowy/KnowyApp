package com.capstone.knowy.data.repository

import com.capstone.knowy.data.api.ApiService
import com.capstone.knowy.data.preference.Preference

class Repository private constructor(
    private val apiService: ApiService,
    private val pref: Preference
) {
    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(
            apiService: ApiService,
            pref: Preference
        ): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository( apiService, pref)
            }.also { instance = it }
    }
}