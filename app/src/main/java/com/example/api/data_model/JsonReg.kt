package com.example.api.data_model

class JsonRegSub : ArrayList<ArrayList<JsonRegSubList>>()
data class JsonRegSubList(
    var fieldId: Int,
    var hint: String,
    var fieldType: String,
    var keyboard: String?,
    var required: Boolean,
    var isActive: Boolean,
    var icon: String
)