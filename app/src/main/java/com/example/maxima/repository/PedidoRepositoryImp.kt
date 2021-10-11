package com.example.maxima.repository

import android.util.Log
import com.example.maxima.api.ApiListener
import com.example.maxima.api.ApiService
import com.example.maxima.api.RetrofitInstance
import com.example.maxima.data.ResponsePedido
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PedidoRepositoryImp:PedidoRepository {

    private var remote: ApiService = RetrofitInstance().createService(ApiService::class.java)


    override fun getPedido(listener: ApiListener<ResponsePedido>) {
         val  call: Call<ResponsePedido> = remote.getPedidos()

        call.enqueue(object : Callback<ResponsePedido>{
            override fun onResponse(
                call: Call<ResponsePedido>,
                response: Response<ResponsePedido>
            ) {
                if (response.code() != 200) {
                    val validation =
                        Gson().fromJson(response.errorBody()!!.string(), String::class.java)
                    listener.onFailure(validation)
                } else {
                    response.body()?.let {
                        listener.onSucess(it)
                    }
                }
            }

            override fun onFailure(call: Call<ResponsePedido>, t: Throwable) {
                Log.d("PedidoRepositoryIMP","Erro")
            }
        })
    }


}