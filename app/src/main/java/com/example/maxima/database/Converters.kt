package com.example.maxima.database

import androidx.room.TypeConverter
import com.example.maxima.data.Contatos
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun listToJson(value: List<Contatos>?): String {

        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<Contatos> {
        val objects = Gson().fromJson(value, Array<Contatos>::class.java) as Array<Contatos>
        val list = objects.toList()
        return list
    }


    @TypeConverter
    fun listToJsonString(value: List<String>?): String {

        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToLisString(value: String): List<String> {
        val objects = Gson().fromJson(value, Array<String>::class.java) as Array<String>
        val list = objects.toList()
        return list
    }
}