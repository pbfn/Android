package com.example.maxima.api


interface ApiListener<T> {

    fun onSucess(body: T)

    fun onFailure(message:String)

}