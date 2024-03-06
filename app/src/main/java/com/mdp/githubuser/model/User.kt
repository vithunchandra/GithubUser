package com.mdp.githubuser.model

import com.google.gson.annotations.SerializedName

data class User(
    @field:SerializedName("login")
    val username: String,

    @field:SerializedName("avatar_url")
    val imageUrl: String
)

data class UserDetail(
    @field:SerializedName("login")
    val username: String,

    @field:SerializedName("avatar_url")
    val imageUrl: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("location")
    val location: String,

    @field:SerializedName("followers")
    val followers: Int,

    @field:SerializedName("following")
    val following: Int,
)