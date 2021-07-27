package com.everis.application.data

import com.google.gson.annotations.SerializedName

data class Persona (
    @SerializedName("id")
    val id: Int,

    @SerializedName("numeroDocumento")
    val numberDocument: String,

    @SerializedName("nombresApellidos")
    val nameLastName: String,

    @SerializedName("direccion")
    val direction: String,

    @SerializedName("celular")
    val cellphoneNumber: String,

    @SerializedName("telefono")
    val phoneNumber: String,

    @SerializedName("sexo")
    val sexOrientation: String,

    @SerializedName("imagenDni1")
    val imageDni1: String,

    @SerializedName("imagenDni2")
    val imageDni2: String,

    @SerializedName("fechaNacimiento")
    val dateBirth: String,

    @SerializedName("tipoDocumento")
    val typeDocument: TipoDocumento,

    @SerializedName("fechaCreacion")
    val dateCreation: String,

    @SerializedName("usuarioCreacion")
    val userCreation: Int,

    @SerializedName("estado")
    val status: String
)