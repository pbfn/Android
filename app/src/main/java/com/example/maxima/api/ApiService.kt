package com.example.maxima.api

import com.example.maxima.data.Pedido
import com.example.maxima.data.ResponseCliente
import com.example.maxima.data.ResponsePedido
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("pedido")
    fun getPedidos(): Call<ResponsePedido>


    @GET("cliente")
    fun getCliente(): Call<ResponseCliente>

}