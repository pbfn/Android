package com.example.maxima.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.maxima.dao.ClienteDao
import com.example.maxima.dao.PedidoDao
import com.example.maxima.data.Cliente
import com.example.maxima.data.Pedido

@Database(entities = [Cliente::class, Pedido::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun clienteDao(): ClienteDao
    abstract fun pedidoDao(): PedidoDao
}