package com.example.maxima.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.maxima.connection.RoomConnection
import com.example.maxima.data.Cliente

class FragmentDadosViewModel : ViewModel() {

    private var _cliente = MutableLiveData<List<Cliente>>()
    var cliente: LiveData<List<Cliente>> = _cliente


    fun getCliente(context: Context) {
        val db = RoomConnection(context).db()
        _cliente.value = db.clienteDao().getAll()
        db.close()
    }

}