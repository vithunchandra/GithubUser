package com.mdp.githubuser.api

import com.mdp.githubuser.model.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    fun searchUser(@Query("q") q: String): Call<SearchResponse>
}