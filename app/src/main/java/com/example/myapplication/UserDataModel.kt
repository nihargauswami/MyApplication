package com.example.myapplication

import com.google.gson.annotations.SerializedName

data class UserDataModel (
    @SerializedName("data") val list : MutableList<DataModel>,
    @SerializedName("subject") val subject : String)
