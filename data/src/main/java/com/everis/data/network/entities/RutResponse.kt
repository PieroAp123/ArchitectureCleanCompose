package com.everis.data.network.entities

import android.util.Log
import com.everis.domain.entities.User
import com.google.gson.annotations.SerializedName

data class RutResponse(
    @field:SerializedName("result")
    var result: Boolean? = null,
    @field: SerializedName("nombre")
    var nombre: String,
    @field: SerializedName("detalle")
    var detalle: String
)
{
    companion object {
        fun toUser(responseRut: RutResponse): User {
            Log.e("Respuesta",responseRut.toString())
            if (responseRut.result != null)
                return User(responseRut.nombre)
            return User("Juan Perez")
        }
    }
}