package com.example.maxima.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.maxima.data.Pedido

@Dao
interface PedidoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg pedido: Pedido)

    @Query("SELECT * FROM pedido")
    fun getAll(): List<Pedido>
}