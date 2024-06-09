package com.capstone.knowy.data.response

import com.google.gson.annotations.SerializedName

data class UserResponse(

    @field:SerializedName("user")
    val user: User,

    @field:SerializedName("status")
    val status: String
)

data class User(

    @field:SerializedName("fullname")
    val fullname: String,

    @field:SerializedName("userId")
    val userId: String,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("username")
    val username: String
)

data class EditProfileResponse(

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("status")
    val status: String? = null
)
