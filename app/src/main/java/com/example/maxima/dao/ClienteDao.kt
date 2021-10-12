package com.example.maxima.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.maxima.data.Cliente

@Dao
interface ClienteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg cliente: Cliente)

    @Query("SELECT * FROM cliente")
    fun getAll(): List<Cliente>
}