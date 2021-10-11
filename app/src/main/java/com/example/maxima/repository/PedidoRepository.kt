package com.example.maxima.repository

import com.example.maxima.api.ApiListener
import com.example.maxima.data.Pedido
import com.example.maxima.data.ResponsePedido

interface PedidoRepository {

    fun getPedido(listener: ApiListener<ResponsePedido>)
}