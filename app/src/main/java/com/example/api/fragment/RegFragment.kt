package com.example.api.fragment

import android.util.Log.e
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.api.R
import com.example.api.adapter.UserRecyclerViewAdapter
import com.example.api.base.BaseFragment
import com.example.api.data_model.UserListItem
import com.example.api.databinding.FragmentRegBinding
import com.example.api.view_model.UserViewModel

class RegFragment : BaseFragment<FragmentRegBinding>(FragmentRegBinding::inflate) {

    private var jsonList = mutableListOf<UserListItem>()
    private var adapterList = mutableListOf<String>()
    private lateinit var adapter: UserRecyclerViewAdapter
    private lateinit var userViewModel: UserViewModel


    override fun create() {
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        val userItem = userViewModel.getUserListItem(requireContext())
        userItem.forEach { userList ->
            userList.forEach {
                jsonList.add(it)
            }
        }
    }

    override fun start() {
        setAdapter()
        listener()
    }


    private fun listener() {

        binding.btnSend.setOnClickListener {
            for (position in 0 until adapter.itemCount) {
                val enteredData = adapter.getEnteredData(position)
                e("getDateAdapter", "$enteredData")

            }
            findNavController().navigate(
                R.id.action_regFragment_to_secondFragment
            )
        }
    }

    private fun setAdapter() {
        adapter = UserRecyclerViewAdapter()
        binding.rcInputs.layoutManager = LinearLayoutManager(requireContext())
        binding.rcInputs.adapter = adapter
        adapter.setData(jsonList)
    }

}