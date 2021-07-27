package com.everis.application.data

import com.google.gson.annotations.SerializedName

data class TipoDocumento (
    @SerializedName("id")
    val id: String,

    @SerializedName("descripcion")
    val description: String,

    @SerializedName("fechaCreacion")
    val dateCreation: String,

    @SerializedName("usuarioCreacion")
    val userCreation: Int,

    @SerializedName("estado")
    val status: String
)