package com.example.maxima.activity

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.maxima.R
import com.example.maxima.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar()
        setView()
    }


    private fun setToolbar() {
        val actionBar = supportActionBar
        actionBar?.setDisplayShowCustomEnabled(true)
        actionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        actionBar?.setCustomView(R.layout.action_bar_main)
    }

    private fun setView() {
        if (isOnline()) {
            binding.imageViewConectado.visibility = View.VISIBLE
            binding.imageViewDesconectado.visibility = View.GONE
        } else {
            binding.imageViewConectado.visibility = View.GONE
            binding.imageViewDesconectado.visibility = View.VISIBLE
        }


        binding.apply {
            buttonClientes.imageViewButton.setImageResource(R.drawable.ic_maxima_pessoas)
            buttonClientes.textViewButton.text = "Clientes"
            buttonClientes.cardClick.setOnClickListener {
                val goToHomePage = Intent(this@MainActivity, HomeActivity::class.java)
                startActivity(goToHomePage)
            }

            buttonFerramentas.imageViewButton.setImageResource(R.drawable.ic_maxima_ferramentas)
            buttonFerramentas.textViewButton.text = "Ferramentas"
            buttonFerramentas.cardClick.setOnClickListener {
                Toast.makeText(this@MainActivity,"Essa funcionalidade ainda não esta habilitada!!!",Toast.LENGTH_LONG).show()
            }

            buttonPedidos.imageViewButton.setImageResource(R.drawable.ic_maxima_pedido)
            buttonPedidos.textViewButton.text = "Pedidos"
            buttonPedidos.cardClick.setOnClickListener {
                Toast.makeText(this@MainActivity,"Essa funcionalidade ainda não esta habilitada!!!",Toast.LENGTH_LONG).show()
            }

            buttonResumo.imageViewButton.setImageResource(R.drawable.ic_maxima_resumo_vendas)
            buttonResumo.textViewButton.text = "Resumo de vendas"
            buttonResumo.cardClick.setOnClickListener {
                Toast.makeText(this@MainActivity,"Essa funcionalidade ainda não esta habilitada!!!",Toast.LENGTH_LONG).show()
            }
        }


    }

    private fun isOnline(): Boolean {
        val manager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

        return manager.activeNetworkInfo != null &&
                manager.activeNetworkInfo!!.isConnectedOrConnecting
    }

    override fun onResume() {
        super.onResume()
        setView()
    }

    override fun onBackPressed() {
        finish()
    }

}