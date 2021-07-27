package com.everis.application.network

import com.everis.application.data.User
import com.google.gson.annotations.SerializedName

data class UserDataResponse(
    @SerializedName("usuarios")
    val users: List<User>
)