package com.example.maxima.data

import com.google.gson.annotations.SerializedName

data class ResponseCliente(

    @SerializedName("cliente")
    val clientes: Cliente

)
