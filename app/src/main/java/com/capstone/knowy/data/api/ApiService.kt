package com.capstone.knowy.data.api

import com.capstone.knowy.data.response.EditProfileResponse
import com.capstone.knowy.data.response.LoginResponse
import com.capstone.knowy.data.response.RegisterResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface ApiService {
    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("email") email: String,
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("confirmPassword") confirmPassword: String
    ): RegisterResponse

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @FormUrlEncoded
    @PUT("profile")
    suspend fun editProfile(
        @Header("Authorization") token:String,
        @Field("fullname") fullname: String,
        @Field("username") username: String
    ): EditProfileResponse
}