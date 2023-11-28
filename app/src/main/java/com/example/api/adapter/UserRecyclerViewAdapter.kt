package com.example.api.adapter

import android.util.Log.e
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.api.data_model.UserListItem
import com.example.api.databinding.RecyclerviewChooserBinding
import com.example.api.databinding.RecyclerviewInputBinding

class UserRecyclerViewAdapter: ListAdapter<UserListItem, RecyclerView.ViewHolder>( object :
    DiffUtil.ItemCallback<UserListItem>() {
    override fun areItemsTheSame(oldItem: UserListItem, newItem: UserListItem): Boolean {
        return oldItem.field_id == newItem.field_id
    }

    override fun areContentsTheSame(oldItem: UserListItem, newItem: UserListItem): Boolean {
        return oldItem == newItem
    }

}) {
    companion object {
        private const val INPUT_JSON = 1
        private const val CHOOSER_JSON = 2
    }
    private var userList = mutableListOf<UserListItem>()

    fun setData(userList: MutableList<UserListItem>) {
        e("userList", "adapter: $userList")
        this.userList.clear()
        this.userList.addAll(userList)
        submitList(userList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == INPUT_JSON) {
            JsonInputViewHolder(
                RecyclerviewInputBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }else {
            JsonChooserViewHolder(
                RecyclerviewChooserBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemViewType(position: Int): Int {

        e("adapterUser", "adapter: ${userList[position].field_type}")
        return if (userList[position].field_type == "input") INPUT_JSON else CHOOSER_JSON

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is JsonInputViewHolder) {
            holder.bind()
        }else if (holder is JsonChooserViewHolder){
            holder.bind()
        }
    }


    inner class JsonInputViewHolder(private val binding: RecyclerviewInputBinding):  RecyclerView.ViewHolder(binding.root){
        fun bind() = with(binding) {
            val input = currentList[adapterPosition]

        }
    }

    inner class JsonChooserViewHolder(private val binding: RecyclerviewChooserBinding):  RecyclerView.ViewHolder(binding.root){
        fun bind() = with(binding) {
            val chooser = currentList[adapterPosition]

        }
    }

}