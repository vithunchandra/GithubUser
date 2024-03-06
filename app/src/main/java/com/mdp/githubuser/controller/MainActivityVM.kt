package com.mdp.githubuser.controller

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mdp.githubuser.api.ApiConfig
import com.mdp.githubuser.model.SearchResponse
import com.mdp.githubuser.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityVM: ViewModel() {
    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object{
        private const val TAG = "MainActivityVM"
    }

    init {
        findUser("a")
    }

    private fun findUser(username: String = ""){
        _isLoading.value = true
        val client = ApiConfig.getApiService().searchUser(username)
        client.enqueue(object: Callback<SearchResponse>{
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                if(response.isSuccessful){
                    _users.value = response.body()?.items
                }else{
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
                _isLoading.value = false
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }
}