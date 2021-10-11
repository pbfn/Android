package com.example.maxima.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object {
        private lateinit var retrofit: Retrofit

        private var base_url = "https://private-anon-095957744f-maximatech.apiary-mock.com/android/"

        fun getRetrofit(): Retrofit {
            if (!::retrofit.isInitialized) {
                retrofit = Retrofit.Builder()
                    .baseUrl(base_url)
                    .client(OkHttpClient.Builder().build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }

    }

    fun <S> createService(service: Class<S>):S{
        return getRetrofit().create(service)
    }
}
