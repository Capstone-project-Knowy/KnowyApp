package com.capstone.knowy.data.api

import com.capstone.knowy.data.response.AddCommentResponse
import com.capstone.knowy.data.response.CommentResponse
import com.capstone.knowy.data.response.CreateForumResponse
import com.capstone.knowy.data.response.EditProfileResponse
import com.capstone.knowy.data.response.ForumDetailResponse
import com.capstone.knowy.data.response.ForumResponse
import com.capstone.knowy.data.response.LoginResponse
import com.capstone.knowy.data.response.RegisterResponse
import com.capstone.knowy.data.response.UserResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

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
        @Header("Authorization") token: String,
        @Field("fullname") fullname: String,
        @Field("username") username: String
    ): EditProfileResponse

    @GET("getUserDetail")
    suspend fun getDetail(
        @Header("Authorization") token: String
    ): UserResponse

    @FormUrlEncoded
    @POST("addForumDiscussion")
    suspend fun addDiscussion(
        @Header("Authorization") token: String,
        @Field("forumTitle") title: String,
        @Field("forumContent") content: String
    ): CreateForumResponse

    @GET("forum")
    suspend fun getForumDiscussion(
        @Header("Authorization") token: String
    ): ForumResponse

    @GET("detailForum/{forumId}")
    suspend fun getDetailForumDiscussion(
        @Path("forumId") id: String,
        @Header("Authorization") token: String
    ): ForumDetailResponse

    @FormUrlEncoded
    @POST("addForumComments")
    suspend fun addComment(
        @Header("Authorization") token: String,
        @Field("forumId") id: String,
        @Field("commentContent") comment: String
    ): AddCommentResponse

    @GET("comment/{forumId}")
    suspend fun getComment(
        @Path("forumId") id: String,
        @Header("Authorization") token: String
    ): CommentResponse

}