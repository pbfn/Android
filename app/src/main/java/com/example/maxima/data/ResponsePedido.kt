package com.example.maxima.data

import com.google.gson.annotations.SerializedName

data class ResponsePedido(
    @SerializedName("pedidos")
    val pedidos: List<Pedido>

)