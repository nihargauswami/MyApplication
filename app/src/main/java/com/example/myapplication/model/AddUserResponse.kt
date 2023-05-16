package com.example.myapplication.model

data class AddUserResponse(
    val message: String,
    val data: UserData,
    val errorCode: String?,
    val error: Boolean
)

data class UserData(
    val mid: Int,
    val uname: String,
    val fullname: String,
    val contact: String,
    val country_code: String,
    val email: String,
    val designation: Int,
    val organisation: String,
    val approval_stage: Int,
    val membership_valid_upto_date: String,
    val is_active: Boolean,
    val postaladdress: String,
    val pin_code: String,
    val country_id: Int,
    val user_role_matrices: List<UserRoleMatrix>
)

data class UserRoleMatrix(
    val user_role_matrix_id: Int,
    val user_id: Int,
    val role_id: Int,
    val created_by: Int,
    val updated_by: Int,
    val createdAt: String,
    val updatedAt: String,
    val role: Role
)

data class Role(
    val role_id: Int,
    val role_name: String,
    val permissions: List<Int>,
    val is_deleted: Boolean,
    val is_active: Boolean,
    val created_by: Int,
    val updated_by: Int,
    val createdAt: String,
    val updatedAt: String
)

