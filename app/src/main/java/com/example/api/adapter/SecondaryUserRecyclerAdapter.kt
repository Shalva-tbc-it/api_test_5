package com.example.api.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.api.R
import com.example.api.data_model.UserList
import com.example.api.data_model.UserListItem
import com.example.api.databinding.RecyclerviewChooserInputBinding
import com.example.api.databinding.RecyclerviewInputBinding

class SecondaryUserRecyclerAdapter() :
    ListAdapter<UserListItem, SecondaryUserRecyclerAdapter.InputChooserViewHolder>(
        object : DiffUtil.ItemCallback<UserListItem>() {
            override fun areItemsTheSame(oldItem: UserListItem, newItem: UserListItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: UserListItem, newItem: UserListItem): Boolean {
                return oldItem == newItem
            }
        }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InputChooserViewHolder {
        return InputChooserViewHolder(
            RecyclerviewInputBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    fun setData(userList: MutableList<UserListItem>) {
        submitList(userList)
    }

    override fun onBindViewHolder(holder: InputChooserViewHolder, position: Int) {
        holder.bind()
    }

    inner class InputChooserViewHolder(private val binding: RecyclerviewInputBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() = with(binding) {
            val input = currentList[adapterPosition]

            edInputBg.hint = input.hint

            val gender = binding.root.context.resources.getStringArray(R.array.gender)
            val arrayAdapter =
                ArrayAdapter(binding.root.context, R.layout.recyclerview_input, gender)

        }
    }
}
