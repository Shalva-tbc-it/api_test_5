package com.example.api.view_model

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.api.data_model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.io.IOException

class UserViewModel() : ViewModel() {

    var userDate = MutableLiveData<String>()

    // For Change
    private val _dataMap = MutableStateFlow<Map<String, Boolean>>(emptyMap())

    // For Read
    val dataMap: StateFlow<Map<String, Boolean>> = _dataMap
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
    fun setData(key: String, isRequired: Boolean, enteredData: String) {
        // check on Required
        if (isRequired && enteredData.isEmpty()) {
            // if empty
            return
        }
        // update map
        _dataMap.value = _dataMap.value + (key to isRequired)
    }


}