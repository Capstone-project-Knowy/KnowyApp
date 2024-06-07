package com.capstone.knowy.data.repository

import android.util.Log
import com.capstone.knowy.data.api.ApiService
import com.capstone.knowy.data.preference.Preference
import com.capstone.knowy.data.preference.UserModel
import com.capstone.knowy.data.result.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

class Repository private constructor(
    private val apiService: ApiService,
    private val pref: Preference
) {
    fun registerUser(email: String, username: String, password: String, confirmPassword: String) = flow<Result<String>> {
        emit(Result.Loading)
        try {
            val response = apiService.register(email, username, password, confirmPassword)
            emit(Result.Success(response.message.toString()))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))

        }
    }

    fun loginUser(email: String, password: String) = flow<Result<String>> {
        emit(Result.Loading)
        try {
            val response = apiService.login(email,password)
            pref.saveAccessToken(response.loginResult.token)
            pref.saveSession(user = UserModel(response.loginResult.name, response.loginResult.token))
            emit(Result.Success(response.status))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
            Log.e("Error Login", "${e.message}", e)
        }
    }

    fun editProfile(fullname: String, username: String) = flow<Result<String>> {
        emit(Result.Loading)
        try {
            val token = "Bearer ${pref.getAccessToken()}"
            Log.d("Token", token)
            val response = apiService.editProfile(token, fullname, username)
            emit(Result.Success(response.message.toString()))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
            Log.e("Error Edit Profile", "${e.message}", e)
        }
    }

    fun getSession(): Flow<UserModel> {
        return pref.getSession()
    }

    fun logOut() = runBlocking {
        pref.saveAccessToken("")
        pref.logout()
    }

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