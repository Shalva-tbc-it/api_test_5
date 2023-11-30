package com.example.api.fragment

import android.widget.Toast
import androidx.fragment.app.viewModels
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
    private var requiredError: Boolean = true
    private lateinit var adapter: UserRecyclerViewAdapter
    private val userViewModel: UserViewModel by viewModels()


    override fun create() {
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
            userViewModel.validateAndNavigate(adapter)
            if (userViewModel.requiredError)
                findNavController().navigate(
                    R.id.action_regFragment_to_secondFragment
                ) else {
                Toast.makeText(requireContext(), "Field inputs", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setAdapter() {
        adapter = UserRecyclerViewAdapter()
        binding.rcInputs.layoutManager = LinearLayoutManager(requireContext())
        binding.rcInputs.adapter = adapter
        adapter.setData(jsonList)
    }

}