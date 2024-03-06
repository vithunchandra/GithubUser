package com.mdp.githubuser.controller

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mdp.githubuser.databinding.UserItemBinding
import com.mdp.githubuser.model.User

class ListUserAdapter(private val setImage: (user: User, imageView: ImageView) -> Unit): ListAdapter<User, ListUserAdapter.MyViewHolder>(
    DIFF_CALLBACK){
    class MyViewHolder(private val binding: UserItemBinding, val setImage: (user: User, imageView: ImageView) -> Unit): RecyclerView.ViewHolder(binding.root){
        fun bind(user: User){
            binding.usernameTv.text = user.username
            setImage(user, binding.userIv)
        }
    }

    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<User>(){
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding, setImage)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = getItem(position)
        Log.d("vithun", user.toString())
        holder.bind(user)
    }
}