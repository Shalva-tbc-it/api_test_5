package com.example.api.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.api.R
import com.example.api.data_model.UserListItem
import com.example.api.databinding.RecyclerviewChooserBinding
import com.example.api.databinding.RecyclerviewInputBinding


class UserRecyclerViewAdapter : ListAdapter<UserListItem, RecyclerView.ViewHolder>(object :
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
    private var getDate = ArrayList<String>()
    private var onItemClickListener: ((userDate: String) -> Unit)? = null
    private lateinit var inputBinding: RecyclerviewInputBinding

    fun setData(userList: MutableList<UserListItem>) {
        this.userList.clear()
        this.userList.addAll(userList)
        submitList(userList)
    }

    private val enteredDataMap = mutableMapOf<Int, String>()

    fun getEnteredData(position: Int): String {
        return enteredDataMap[position] ?: ""
    }

    fun setOnItemClickListener(listener: (userDate: String) -> Unit) {
        onItemClickListener = listener
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
        } else {
            JsonChooserViewHolder(
                RecyclerviewChooserBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemViewType(position: Int) =
        if (position % 6 < 3) {
            if (userList[position].field_type == "input") INPUT_JSON else CHOOSER_JSON
        } else {
            if (userList[position].field_type == "input") INPUT_JSON else CHOOSER_JSON
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is JsonInputViewHolder) {
            holder.bind()

        } else if (holder is JsonChooserViewHolder) {
            holder.bind()
        }


    }

    inner class JsonInputViewHolder(val binding: RecyclerviewInputBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() = with(binding) {
            inputBinding = binding
            val input = currentList[adapterPosition]
            edInput.hint = input.hint
            edInput.doOnTextChanged { text, _, _, _ ->
                enteredDataMap[adapterPosition] = text.toString()
            }

            if (adapterPosition % 3 == 2) {
                root.setBackgroundResource(R.drawable.input_corner_bottom)
                val layoutParams = root.layoutParams as ViewGroup.MarginLayoutParams
                layoutParams.bottomMargin = 30
            } else if (adapterPosition % 3 == 0) {
                root.setBackgroundResource(R.drawable.input_corner_top)
            }else if (adapterPosition %  3 == 1){
                root.setBackgroundResource(R.drawable.input_corner_center)
            }
        }

    }

    inner class JsonChooserViewHolder(private val binding: RecyclerviewChooserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() = with(binding) {
            val chooser = currentList[adapterPosition]


            if (adapterPosition % 3 == 2) {
                root.setBackgroundResource(R.drawable.input_corner_bottom)
                val layoutParams = root.layoutParams as ViewGroup.MarginLayoutParams
                layoutParams.bottomMargin = 30
            } else if (adapterPosition % 3 == 0) {
                root.setBackgroundResource(R.drawable.input_corner_top)
            }else if (adapterPosition %  3 == 1){
                root.setBackgroundResource(R.drawable.input_corner_center)
            }

        }
    }
}