package com.example.maxima.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.maxima.R
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
        getCliente()
    }


    private fun setupViewModel() {
        splashViewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
    }

    private fun observeData() {
        splashViewModel.cliente.observe(this, {
            getPedido()
        })

        splashViewModel.pedido.observe(this, {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        })
    }

    private fun getCliente() {
        splashViewModel.getCliente(this)
    }

    private fun getPedido() {
        splashViewModel.getPedido(this)
    }

}