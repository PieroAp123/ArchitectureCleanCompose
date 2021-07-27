package com.everis.application.network

import com.everis.data.network.entities.RutResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterfaceCikla {


    @GET("usuarios")
    fun getUsers(): Call<UserDataResponse>

    @POST("MEL-Login")
    fun doLogin(@Body params: RequestBody): Call<RutResponse>
}