package com.example.maxima.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.maxima.connection.RoomConnection
import com.example.maxima.data.Pedido


class HistoricoFragmentViewModel : ViewModel() {

    private var _pedidos = MutableLiveData<List<Pedido>>()
    var pedidos: LiveData<List<Pedido>> = _pedidos


    fun getPedidos(context: Context) {
        val db = RoomConnection(context).db()
        _pedidos.value = db.pedidoDao().getAll()
    }

}