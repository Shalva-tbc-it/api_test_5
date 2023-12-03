package com.example.api.data_model

import com.google.gson.annotations.SerializedName

data class UserListItem(
    @SerializedName("field_id")
    val fieldId: Int?,
    @SerializedName("field_type")
    val fieldType: String?,
    val hint: String?,
    val icon: String?,
    @SerializedName("is_active")
    val isActive: Boolean?,
    val keyboard: String? = null,
    val required: Boolean?

)
enum class FieldType {
    INPUT,
    CHOOSER
}