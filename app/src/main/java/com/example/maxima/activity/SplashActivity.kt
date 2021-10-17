package com.example.maxima.activity

import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.maxima.databinding.ActivitySplashBinding
import com.example.maxima.viewModel.SplashViewModel

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setupViewModel()
        observeData()
        getConnection()
    }


    private fun setupViewModel() {
        splashViewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
    }

    private fun observeData() {

        splashViewModel.isOnline.observe(this, { isOnline ->
            if (isOnline) {
                getCliente()
            } else {
                getFromDB()
            }
        })

        splashViewModel.cliente.observe(this, {
            getPedido()
        })

        splashViewModel.pedido.observe(this, {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        })

        splashViewModel.clienteFromDB.observe(this, { clientes ->
            if (clientes.isNotEmpty()) {

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                setupAlert()
            }
        })
    }

    private fun getCliente() {
        splashViewModel.getCliente(this)
    }

    private fun getPedido() {
        splashViewModel.getPedido(this)
    }

    private fun getConnection() {
        val manager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        splashViewModel.getConnection(manager)
    }

    private fun getFromDB() {
        splashViewModel.getFromDB(this)
    }


    private fun setupAlert() {

        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder.setNegativeButton("Fechar") { _, _ ->
            getConnection()
        }
        builder.setTitle("Você não esta conectado")
        builder.setMessage("Por favor se conecte à internet antes de continuar")
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }


}