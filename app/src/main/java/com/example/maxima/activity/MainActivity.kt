package com.example.maxima.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.maxima.R
import com.example.maxima.viewModel.SplashViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setupViewModel()
        //observeData()
    }

    private fun setupViewModel() {
        splashViewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
        splashViewModel.getCliente(this)
        splashViewModel.getPedido(this)
    }


    private fun observeData() {
        splashViewModel.cliente.observe(this, { cliente ->
            //Toast.makeText(this, cliente.nomeFantasia, Toast.LENGTH_LONG).show()
        })
    }

}