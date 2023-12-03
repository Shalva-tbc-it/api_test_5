package com.example.api.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.api.R
import com.example.api.data_model.FieldType
import com.example.api.data_model.UserList
import com.example.api.data_model.UserListItem
import com.example.api.databinding.RecyclerviewChooserInputBinding


class MainUserRecyclerViewAdapter :
    ListAdapter<UserListItem, MainUserRecyclerViewAdapter.JsonInputViewHolder>(object :
        DiffUtil.ItemCallback<UserListItem>() {
        override fun areItemsTheSame(oldItem: UserListItem, newItem: UserListItem): Boolean {
            return oldItem.fieldId == newItem.fieldId
        }

        override fun areContentsTheSame(oldItem: UserListItem, newItem: UserListItem): Boolean {
            return oldItem == newItem
        }
    }) {

//    private var secondaryAdapter: SecondaryUserRecyclerAdapter? = null
    private lateinit var inputBinding: RecyclerviewChooserInputBinding

    private var userList = mutableListOf<UserList>()
    private var onItemClickListener: ((userDate: String) -> Unit)? = null


    fun setData(userList: MutableList<UserList>) {
        this.userList.clear()
        this.userList.addAll(userList)
        submitList(userList[0])
    }

    private val dataMap = mutableMapOf<String, String>()

    // get data in Map
    fun getEnteredData(position: Int): String? {
        val item = getItem(position)
        return dataMap[item.hint]
    }


    fun setOnItemClickListener(listener: (userDate: String) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainUserRecyclerViewAdapter.JsonInputViewHolder {

        return JsonInputViewHolder(
            RecyclerviewChooserInputBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: JsonInputViewHolder, position: Int) {
        holder.bind()
    }

    inner class JsonInputViewHolder(private val binding: RecyclerviewChooserInputBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val secondaryAdapter: SecondaryUserRecyclerAdapter = SecondaryUserRecyclerAdapter(userList)
        private var isSecondaryRecyclerViewInitialized = false

        init {
            binding.secondaryRecyclerView.layoutManager = LinearLayoutManager(binding.root.context)
            binding.secondaryRecyclerView.adapter = secondaryAdapter
        }

        fun bind() = with(binding) {
            inputBinding = binding
            val input = currentList[adapterPosition]
            if (input.fieldType?.lowercase() == FieldType.INPUT.toString().lowercase()) {
                edInput.hint = input.hint

                spinnerChooser.visibility = View.GONE

            }else if (input.fieldType?.lowercase() == FieldType.CHOOSER.toString().lowercase()) {
                spinnerChooser.visibility = View.VISIBLE

                val gender = binding.root.context.resources.getStringArray(R.array.gender)
                val arrayAdapter = ArrayAdapter(binding.root.context, R.layout.recyclerview_chooser, gender)

                binding.spinnerChooser.setAdapter(arrayAdapter)
            }
            if (!isSecondaryRecyclerViewInitialized) {
                val secondaryAdapterData = userList[1]
                secondaryAdapter.submitList(secondaryAdapterData)
                isSecondaryRecyclerViewInitialized = true
            }


            edInput.doOnTextChanged { text, _, _, _ ->
                val hintKey = input.hint?.toString() ?: ""
                dataMap[hintKey] = text.toString()
            }

            if (adapterPosition % 3 == 2) {
                root.setBackgroundResource(R.drawable.input_corner_bottom)
                val layoutParams = root.layoutParams as ViewGroup.MarginLayoutParams
                layoutParams.bottomMargin = 30
            } else if (adapterPosition % 3 == 0) {
                root.setBackgroundResource(R.drawable.input_corner_top)
            } else if (adapterPosition % 3 == 1) {
                root.setBackgroundResource(R.drawable.input_corner_center)
            }
        }
    }
}