package com.example.maxima.repository

import android.util.Log
import com.example.maxima.api.ApiListener
import com.example.maxima.api.ApiService
import com.example.maxima.api.RetrofitInstance
import com.example.maxima.data.ResponseCliente
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClienteRepositoryImp : ClienteRepository {

    private var remote: ApiService = RetrofitInstance().createService(ApiService::class.java)


    override fun getCliente(listener: ApiListener<ResponseCliente>) {
        val call: Call<ResponseCliente> = remote.getCliente()
        call.enqueue(object : Callback<ResponseCliente> {
            override fun onResponse(
                call: Call<ResponseCliente>,
                response: Response<ResponseCliente>
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

            override fun onFailure(call: Call<ResponseCliente>, t: Throwable) {
                Log.d("ClienteRepositoryImp","Erro")
            }

        })

    }
}