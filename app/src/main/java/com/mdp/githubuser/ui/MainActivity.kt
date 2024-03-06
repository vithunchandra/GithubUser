package com.mdp.githubuser.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.mdp.githubuser.R
import com.mdp.githubuser.controller.ListUserAdapter
import com.mdp.githubuser.controller.MainActivityVM
import com.mdp.githubuser.databinding.ActivityMainBinding
import com.mdp.githubuser.model.User

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            searchView.setupWithSearchBar(searchBar)
        }

        val layoutManager = LinearLayoutManager(this)
        binding.listUserRv.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.listUserRv.addItemDecoration(itemDecoration)

        val mainViewModel = ViewModelProvider(this)[MainActivityVM::class.java]
        mainViewModel.users.observe(this){users ->
            setUserList(users)
        }
        mainViewModel.isLoading.observe(this){
            showLoading(it)
        }
    }

    private fun setUserList(users: List<User>){
        val adapter = ListUserAdapter{user, imageView ->
            Glide.with(this)
                .load(user.imageUrl)
                .into(imageView)
        }
        adapter.submitList(users)
        binding.listUserRv.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean){
        if(isLoading){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }
}