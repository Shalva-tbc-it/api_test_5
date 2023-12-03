package com.example.api.fragment

import android.util.Log.e
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.api.base.BaseFragment
import com.example.api.databinding.FragmentSecondBinding
import com.example.api.view_model.UserViewModel
import kotlinx.coroutines.launch

class SecondFragment : BaseFragment<FragmentSecondBinding>(FragmentSecondBinding::inflate) {

    private val userViewModel: UserViewModel by activityViewModels()

    override fun create() {

    }

    override fun start() {
        viewLifecycleOwner.lifecycleScope.launch {
            userViewModel.dataMap.collect { dataMap ->
                updateUI(dataMap)
                e("datamap", "datasecond: $dataMap")
            }
        }
    }

    private fun updateUI(dataMap: Map<String, String>) {
        binding.tvUserName.text = dataMap["UserName"]
        binding.tvEmail.text = dataMap["Email"]
        binding.tvPhone.text = dataMap["phone"]
    }
}