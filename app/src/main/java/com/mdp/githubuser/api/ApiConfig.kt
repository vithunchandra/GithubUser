package com.mdp.githubuser.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object{
        fun getApiService(): ApiService{
            val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val authInterceptor = Interceptor{chain ->
                val req = chain.request()
                val requestHeaders = with(req.newBuilder()){
                    addHeader("Authorization", "token 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")
                    build()
                }
                chain.proceed(requestHeaders)
            }
            val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor).addInterceptor(authInterceptor).build()

            val retrofit = with(Retrofit.Builder()){
                baseUrl("https://api.github.com/")
                addConverterFactory(GsonConverterFactory.create())
                client(client)
                build()
            }
            return retrofit.create(ApiService::class.java)
        }
    }
}