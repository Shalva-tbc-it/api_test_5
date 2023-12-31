package com.example.api.fragment

import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.api.R
import com.example.api.adapter.MainUserRecyclerViewAdapter
import com.example.api.adapter.SecondaryUserRecyclerAdapter
import com.example.api.base.BaseFragment
import com.example.api.data_model.User
import com.example.api.data_model.UserList
import com.example.api.databinding.FragmentRegBinding
import com.example.api.view_model.UserViewModel

class RegFragment : BaseFragment<FragmentRegBinding>(FragmentRegBinding::inflate) {

    private var jsonList = mutableListOf<UserList>()
    private lateinit var mainAdapter: MainUserRecyclerViewAdapter
    private lateinit var secondAdapter: SecondaryUserRecyclerAdapter
    private val userViewModel: UserViewModel by activityViewModels()


    override fun create() {
        val userItem = userViewModel.getUserListItem(requireContext())
        userItem.forEach {
            jsonList.add(it)
        }
    }

    override fun start() {
        setAdapter()
        listener()
    }


    private fun listener() {

        binding.btnSend.setOnClickListener {
            userViewModel.validateAndNavigate(secondAdapter)
            if (userViewModel.requiredError)
                findNavController().navigate(
                    R.id.action_regFragment_to_secondFragment
                ) else {
                Toast.makeText(requireContext(), "Field inputs", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setAdapter() {
        mainAdapter = MainUserRecyclerViewAdapter()
        binding.rcMainInputs.layoutManager = LinearLayoutManager(requireContext())
        binding.rcMainInputs.adapter = mainAdapter
        mainAdapter.setData(jsonList)
    }

}