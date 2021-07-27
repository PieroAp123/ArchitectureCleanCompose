package com.everis.application.screens.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.everis.application.network.ApiInterfaceCikla
import com.everis.application.network.ApiServiceCikla
import com.everis.application.network.UserDataResponse
import com.everis.application.utils.DispatcherProvider
import com.everis.data.network.entities.RutResponse
import com.everis.domain.entities.*
import com.everis.domain.extensions.toError
import com.everis.domain.usecases.LoginUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by WilderCs on 2019-10-22.
 * Copyright (c) 2019 Everis. All rights reserved.
 **/

open class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val dispatcherProvider: DispatcherProvider = DispatcherProvider()
) : ViewModel() {

    val userLiveData = MutableLiveData<User>()
    val errorLiveData = MutableLiveData<CustomError>()
    val loadingLiveData = MutableLiveData<Boolean>()
    private val api = ApiServiceCikla().getRetrofit()
    private val api2 = ApiServiceCikla().getRetrofit2()
    private val userListString = MutableLiveData<UserDataResponse>()

    fun doLogin(username: String, password: String) {

        GlobalScope.launch(dispatcherProvider.IO + CoroutineExceptionHandler { _, ex ->
            errorLiveData.postValue(ex.toError())
            loadingLiveData.postValue(false)
        }) {
            loadingLiveData.postValue(true)
            when (val user = loginUseCase.doLogin(username, password)) {
                is CustomResult.OnSuccess -> {
                    userLiveData.postValue(user.data!!)
                }
                is CustomResult.OnError -> {
                    errorLiveData.postValue(user.error)
                }
            }
            loadingLiveData.postValue(false)
        }
    }

    fun doLogin2(username: String, password: String) {

        val paramObject = JSONObject()
        paramObject.put("rut", username)
        paramObject.put("pass", password)

        val infoObject = JSONObject()
        infoObject.put("sessionCode", "202006110930" )
        paramObject.put("info", infoObject)
        paramObject.put("visibleContext", "context")
        paramObject.put("openContext","context")
        paramObject.put("hiddenContext","context")

        val params = paramObject.toString()
            .toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())

        val service: ApiInterfaceCikla = api2.create(ApiInterfaceCikla::class.java)
        val callApi: Call<RutResponse> = service.doLogin(params)

        callApi.enqueue(object: Callback<RutResponse> {
            override fun onResponse(call: Call<RutResponse>, response: Response<RutResponse>) {
                val responseCall =  response.body()
                val detailResponseLogin = responseCall?.detalle
                if (responseCall?.result == true)
                    CustomResult.OnSuccess(RutResponse.run { toUser(responseCall) })
                else
                    CustomResult.OnError(CustomNotFoundError(message = detailResponseLogin))
            }

            override fun onFailure(call: Call<RutResponse>, t: Throwable) {
                print("error")
            }

        })

        /*return when(callApi.isSuccessful) {
            true -> {
                val response: RutResponse? = callApi.body()
                val detailResponseLogin = response?.detalle
                if (response?.result == true)
                    CustomResult.OnSuccess(RutResponse.run { toUser(response) })
                else
                    CustomResult.OnError(CustomNotFoundError(message = detailResponseLogin))
            }
            false -> {
                CustomResult.OnError(
                    HttpError(code = callApi.code(), message = callApi.message())
                )
            }
        }*/
    }

    fun getUser() {
        val service: ApiInterfaceCikla = api.create(ApiInterfaceCikla::class.java)
        val result: Call<UserDataResponse> = service.getUsers()
        result.enqueue(object : retrofit2.Callback<UserDataResponse> {

            override fun onResponse(
                call: Call<UserDataResponse>,
                response: Response<UserDataResponse>
            ) {
                val users = response.body()
                userListString.postValue(users!!)
                Log.e("Usuarios", users.toString())
                Log.e("Success", "Entró al call")
            }

            override fun onFailure(call: Call<UserDataResponse>, t: Throwable) {
                Log.e("Servicio Usuarios", "Error servicio de usuarios")
            }


        })
    }
}