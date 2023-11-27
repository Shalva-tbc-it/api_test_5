package com.example.api.fragment

import android.app.Application
import com.example.api.base.BaseFragment
import com.example.api.data_model.JsonReg
import com.example.api.databinding.FragmentRegBinding
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class RegFragment : BaseFragment<FragmentRegBinding>(FragmentRegBinding::inflate) {

    var jsonList = arrayListOf<JsonReg>()
    override fun create() {
        readJson()
    }

    override fun start() {

    }

    private fun readJson() {
        try {
            val obj = JSONObject(getJSONFromAssets()!!)

            val inputArray = obj.getJSONArray("RegTypes.json")

            for (i in 0..<inputArray.length()) {

                val input = inputArray.getJSONObject(i)

                val id = input.getInt("field_id")
                val hint = input.getString("hint")
                val fieldType = input.getString("field_type")
                val keyboard = input.getString("keyboard")
                val required = input.getBoolean("required")
                val isActive = input.getBoolean("is_active")
                val icon = input.getString("is_active")

                val inputTypes = JsonReg(
                    fieldId = id, hint = hint, fieldType = fieldType, keyboard = keyboard,
                    required = required, isActive = isActive, icon = icon
                )

                jsonList.add(inputTypes)
            }
        } catch (e: JSONException) {
            e.stackTrace
        }

    }

    private fun getJSONFromAssets(): String? {

        var json: String? = null
        val charset: Charset = Charsets.UTF_8
        try {
            val jsonFile = requireContext().assets.open("RegTypes.json")
            val size = jsonFile.available()
            val buffer = ByteArray(size)
            jsonFile.read(buffer)
            jsonFile.close()
            json = String(buffer, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }
//    val jsonFile: String =
//        requireContext().assets.open("RegTypes.json").bufferedReader().use { it.readText() }

//
//    val jsonArray = JSONArray(fileInString)
//
//    for (i in 0..jsonArray.length()) {
//        var jsonObject = jsonArray.getJSONObject(i)
//    }

}