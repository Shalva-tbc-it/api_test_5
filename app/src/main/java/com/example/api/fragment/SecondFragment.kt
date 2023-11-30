package com.example.api.fragment

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.api.base.BaseFragment
import com.example.api.databinding.FragmentSecondBinding
import com.example.api.view_model.UserViewModel

class SecondFragment : BaseFragment<FragmentSecondBinding>(FragmentSecondBinding::inflate) {

    private lateinit var userViewModel: UserViewModel

    override fun create() {
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

    }

    override fun start() {
        userViewModel.userDate.observe(viewLifecycleOwner, Observer {
            binding.tvUserName.text = it.toString()
        })
    }

}