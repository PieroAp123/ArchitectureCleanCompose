package com.everis.data.network.repository

import com.everis.data.network.ApiConfig
import com.everis.data.network.entities.LoginResponse
import com.everis.domain.entities.CustomNotFoundError
import com.everis.domain.entities.CustomResult
import com.everis.domain.entities.HttpError
import com.everis.domain.entities.User
import com.everis.domain.repository.LoginRepositoryNetwork
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import org.json.JSONObject

class LoginRepository(private val apiConfig: ApiConfig): LoginRepositoryNetwork {

    override fun doLogin(username: String, password: String): CustomResult<User> {

        val paramObject = JSONObject()
        paramObject.put("username", username)
        paramObject.put("password", password)

        val params = RequestBody.create(
            "application/json; charset=utf-8".toMediaTypeOrNull(),
            paramObject.toString()
        )

        val callApi = apiConfig.doLogin(params).execute()
        return when(callApi.isSuccessful) {
            true -> {
                val response: LoginResponse? = callApi.body()
                if (response?.status?.code == 1)
                    CustomResult.OnSuccess(LoginResponse.run { toUser(response) })
                else
                    CustomResult.OnError(CustomNotFoundError(message = response?.status?.message))
            }
            false -> {
                CustomResult.OnError(
                    HttpError(code = callApi.code(), message = callApi.message())
                )
            }
        }
    }

}