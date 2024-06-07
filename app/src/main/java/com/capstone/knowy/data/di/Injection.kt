package com.capstone.knowy.data.di

import android.content.Context
import com.capstone.knowy.data.api.ApiConfig
import com.capstone.knowy.data.preference.Preference
import com.capstone.knowy.data.preference.dataStore
import com.capstone.knowy.data.repository.Repository

object Injection {
    fun provideRepository(context: Context): Repository {
        val pref = Preference.getInstance(context.dataStore)
        val apiService = ApiConfig.getApiService()
        return Repository.getInstance(apiService, pref)
    }
}