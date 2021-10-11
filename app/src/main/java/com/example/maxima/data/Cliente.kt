package com.example.maxima.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Cliente(
    @SerializedName("id")
    @PrimaryKey(autoGenerate = false)
    val id: String,

    @SerializedName("codigo")
    @ColumnInfo(name = "codigo")
    val codigo: String,

    @SerializedName("razao_social")
    @ColumnInfo(name = "razao_social")
    val razao_social: String,

    @SerializedName("nomeFantasia")
    @ColumnInfo(name = "nomeFantasia")
    val nomeFantasia: String,

    @SerializedName("cnpj")
    @ColumnInfo(name = "cnpj")
    val cnpj: String,

    @SerializedName("ramo_atividade")
    @ColumnInfo(name = "ramo_atividade")
    val ramo_atividade: String,

    @SerializedName("endereco")
    @ColumnInfo(name = "endereco")
    val endereco: String,

    @SerializedName("status")
    @ColumnInfo(name = "status")
    val status: String,

    @SerializedName("contatos")
    @ColumnInfo(name = "contatos")
    val contatos: List<Contatos>,
)