package com.example.api.fragment

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.api.adapter.UserRecyclerViewAdapter
import com.example.api.base.BaseFragment
import com.example.api.data_model.UserListItem
import com.example.api.databinding.FragmentRegBinding
import com.example.api.view_model.UserViewModel

class RegFragment : BaseFragment<FragmentRegBinding>(FragmentRegBinding::inflate) {

    private var jsonList = mutableListOf<UserListItem>()
    private lateinit var adapter: UserRecyclerViewAdapter
    private lateinit var userViewModel: UserViewModel


    override fun create() {

    }

    override fun start() {
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        val userItem = userViewModel.getUserListItem(requireContext())
        userItem.forEach { userList ->
            userList.forEach {
                jsonList.add(it)
            }
        }
        setAdapter()
    }

    private fun setAdapter() {
        adapter = UserRecyclerViewAdapter()
        binding.rcInputs.layoutManager = LinearLayoutManager(requireContext())
        binding.rcInputs.adapter = adapter
        adapter.setData(jsonList)
    }

}