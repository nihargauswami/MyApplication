package com.example.myapplication.Model

data class PostData(
    val fullname: String,
    val contact: String,
    val country_code: String,
    val password: String,
    val email: String,
    val designation: String,
    val organisation: String,
    val postaladdress: String,
    val location: String,
    val pin_code: String,
    val country_id: Int,
    val oauth_provider: Int,
    val industryIds: List<String>,
    val expertiseIds: List<String>,
    val isConsentAgreeed: Boolean
)

