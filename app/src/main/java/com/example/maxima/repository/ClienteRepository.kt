package com.example.maxima.repository

import com.example.maxima.api.ApiListener
import com.example.maxima.data.ResponseCliente

interface ClienteRepository {

    fun getCliente(listener: ApiListener<ResponseCliente>)

}