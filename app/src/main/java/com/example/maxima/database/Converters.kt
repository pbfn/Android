package com.example.maxima.database

import androidx.room.TypeConverter
import com.example.maxima.Entities.EntityCritica
import com.example.maxima.Entities.EntityLegenda
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
    fun listToJsonString(value: List<EntityLegenda>?): String {

        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToLisString(value: String): List<EntityLegenda> {
        return if (value == "[]" || value == "null") {
            mutableListOf(EntityLegenda.VAZIO)
        } else {
            val objects =
                Gson().fromJson(value, Array<EntityLegenda>::class.java) as Array<EntityLegenda>
            objects.toList()
        }

    }


    @TypeConverter
    fun listToJsonEntityCritica(value: List<EntityCritica>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToEntityCritica(value: String): List<EntityCritica> {
        return if (value == "[]" || value == "null") {
            mutableListOf(EntityCritica.VAZIO)
        } else {
            val objects =
                Gson().fromJson(value, Array<EntityCritica>::class.java) as Array<EntityCritica>
            objects.toList()
        }

    }
}