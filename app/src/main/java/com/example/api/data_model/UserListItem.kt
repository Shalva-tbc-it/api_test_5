package com.example.api.data_model

data class UserListItem(
    val field_id: Int?,
    val field_type: String?,
    val hint: String?,
    val icon: String?,
    val is_active: Boolean?,
    val keyboard: String? = null,
    val required: Boolean?
)