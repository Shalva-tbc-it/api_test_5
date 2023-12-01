package com.example.api.view_model

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.api.adapter.UserRecyclerViewAdapter
import com.example.api.data_model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.io.IOException

class UserViewModel() : ViewModel() {

    private var _dataMap = MutableStateFlow<Map<String, String>>(emptyMap())
    val dataMap: StateFlow<Map<String, String>> = _dataMap
    var requiredError = false
    fun getUserListItem(context: Context): User {
        lateinit var jsonString: String
        try {
            jsonString = context.assets.open("RegTypes.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            ioException.stackTrace
        }
        val listUserType = object : TypeToken<User>() {}.type
        return Gson().fromJson(jsonString, listUserType)
    }

    // add to map
    fun setData(hint: String, enteredData: String) {
        val updatedMap = _dataMap.value.toMutableMap()
        updatedMap[hint] = enteredData
        _dataMap.value = updatedMap.toMap()
    }

    fun validateAndNavigate(adapter: UserRecyclerViewAdapter) {

        for (position in 0 until adapter.itemCount) {
            val enteredData = adapter.getEnteredData(position)
            val item = adapter.currentList[position]

            item?.hint?.let { hint ->
                item.required?.let { required ->
                    if (required && enteredData.isNullOrEmpty()) {
                        requiredError = false
                    }
                    enteredData?.let {
                        setData(hint, enteredData)
                        requiredError = true
                    }
                }
            }
        }
    }

}