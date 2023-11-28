package com.example.api.view_model

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.api.data_model.User
import com.example.api.data_model.UserList
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class UserViewModel(): ViewModel() {

    val userItemList = MutableLiveData<UserList>()

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

}