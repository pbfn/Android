package com.example.maxima.connection

import android.content.Context
import androidx.room.Room
import com.example.maxima.database.AppDataBase

class RoomConnection(private val context: Context) {

    fun db(): AppDataBase = Room.databaseBuilder(
        context,
        AppDataBase::class.java,
        "maxima"
    )
        .fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build()
}