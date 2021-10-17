package com.example.maxima.viewModel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.work.WorkManager
import com.example.maxima.api.ApiListener
import com.example.maxima.connection.RoomConnection
import com.example.maxima.data.Cliente
import com.example.maxima.data.ResponseCliente
import com.example.maxima.data.ResponsePedido
import com.example.maxima.database.AppDataBase
import com.example.maxima.repository.ClienteRepositoryImp
import com.example.maxima.repository.PedidoRepositoryImp

class SplashViewModel() : ViewModel() {


    private val repositoryCliente = ClienteRepositoryImp()
    private val repositoryPedido = PedidoRepositoryImp()

    private var _cliente = MutableLiveData<ResponseCliente>()
    var cliente: LiveData<ResponseCliente> = _cliente

    private var _pedidos = MutableLiveData<ResponsePedido>()
    var pedido: LiveData<ResponsePedido> = _pedidos

    private var _errorMessage = MutableLiveData<String>()
    var errorMessage: LiveData<String> = _errorMessage


    private var _clienteFromDB = MutableLiveData<List<Cliente>>()
    var clienteFromDB: LiveData<List<Cliente>> = _clienteFromDB

    private var _isOnline = MutableLiveData<Boolean>()
    var isOnline: LiveData<Boolean> = _isOnline


    fun getCliente(context: Context) {
        val db = RoomConnection(context).db()
        repositoryCliente.getCliente(object : ApiListener<ResponseCliente> {
            override fun onSucess(body: ResponseCliente) {
                _cliente.value = body
                insertDB(db, body.clientes)
            }

            override fun onFailure(message: String) {
                _errorMessage.value = message
            }

        })
    }

    fun getPedido(context: Context) {
        val db = RoomConnection(context).db()
        repositoryPedido.getPedido(object : ApiListener<ResponsePedido> {
            override fun onSucess(body: ResponsePedido) {
                _pedidos.value = body
                insertPedidoDB(db, body)
            }

            override fun onFailure(message: String) {
                _errorMessage.value = message
            }

        })
    }

    private fun insertDB(db: AppDataBase, cliente: Cliente) {
        db.clienteDao().insertAll(cliente)
    }

    private fun insertPedidoDB(db: AppDataBase, pedidos: ResponsePedido) {
        for (pedido in pedidos.pedidos) {
            db.pedidoDao().insertAll(pedido)
        }
    }

    fun getFromDB(context: Context) {
        val db = RoomConnection(context).db()
        _clienteFromDB.value = db.clienteDao().getAll()
    }

    fun getConnection(manager: ConnectivityManager) {
        _isOnline.value = (manager.activeNetworkInfo != null &&
                manager.activeNetworkInfo!!.isConnectedOrConnecting)
    }

}