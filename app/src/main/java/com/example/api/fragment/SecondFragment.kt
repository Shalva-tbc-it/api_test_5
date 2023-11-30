package com.example.api.fragment

import androidx.fragment.app.viewModels
import com.example.api.base.BaseFragment
import com.example.api.databinding.FragmentSecondBinding
import com.example.api.view_model.UserViewModel

class SecondFragment : BaseFragment<FragmentSecondBinding>(FragmentSecondBinding::inflate) {

    private val userViewModel: UserViewModel by viewModels()

    override fun create() {

    }

    override fun start() {

    }

}