package com.example.maxima.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Pedido(

    @SerializedName("numero_ped_Rca")
    @PrimaryKey(autoGenerate = false)
    val id: Int,

    @SerializedName("numero_ped_erp")
    @ColumnInfo(name = "numero_ped_erp")
    val numero_ped_erp: String,

    @SerializedName("codigoCliente")
    @ColumnInfo(name = "codigoCliente")
    val codigoCliente: String,

    @SerializedName("NOMECLIENTE")
    @ColumnInfo(name = "NOMECLIENTE")
    val NOMECLIENTE: String,

    @SerializedName("data")
    @ColumnInfo(name = "data")
    val data: String,

    @SerializedName("critica")
    @ColumnInfo(name = "critica")
    val critica: String?,

    @SerializedName("tipo")
    @ColumnInfo(name = "tipo")
    val tipo: String,

    @SerializedName("legendas")
    @ColumnInfo(name = "legendas")
    val legendas: List<String>?,

)