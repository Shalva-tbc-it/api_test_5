package com.example.api.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.api.R
import com.example.api.data_model.FieldType
import com.example.api.data_model.UserList
import com.example.api.data_model.UserListItem
import com.example.api.databinding.RecyclerviewChooserInputBinding

class SecondaryUserRecyclerAdapter(private val userList: List<UserList>) :
    ListAdapter<UserListItem, SecondaryUserRecyclerAdapter.InputChooserViewHolder>(
        object : DiffUtil.ItemCallback<UserListItem>() {
            override fun areItemsTheSame(oldItem: UserListItem, newItem: UserListItem): Boolean {
                return oldItem.fieldId == newItem.fieldId
            }

            override fun areContentsTheSame(oldItem: UserListItem, newItem: UserListItem): Boolean {
                return oldItem == newItem
            }
        }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InputChooserViewHolder {
        return InputChooserViewHolder(
            RecyclerviewChooserInputBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    fun setData(userList: MutableList<UserList>) {
        submitList(userList[1])
    }

    override fun onBindViewHolder(holder: InputChooserViewHolder, position: Int) {
        holder.bind()
    }

    inner class InputChooserViewHolder(private val binding: RecyclerviewChooserInputBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() = with(binding) {
            val input = currentList[adapterPosition]
            if (input.fieldType?.lowercase() == FieldType.CHOOSER.toString().lowercase()) {
                spinnerChooser.visibility = View.VISIBLE
                edInput.visibility = View.GONE


                val gender = binding.root.context.resources.getStringArray(R.array.gender)
                val arrayAdapter = ArrayAdapter(binding.root.context, R.layout.recyclerview_chooser, gender)

                binding.spinnerChooser.setAdapter(arrayAdapter)
            }else {
                spinnerChooser.visibility = View.GONE
                edInput.visibility = View.VISIBLE

                val gender = binding.root.context.resources.getStringArray(R.array.gender)
                val arrayAdapter = ArrayAdapter(binding.root.context, R.layout.recyclerview_chooser, gender)

                binding.spinnerChooser.setAdapter(arrayAdapter)
            }

            // Используйте данные item для настройки элемента вторичного RecyclerView
        }
    }
}
