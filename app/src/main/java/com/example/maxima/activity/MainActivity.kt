package com.example.maxima.activity

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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

    private fun setView(){
        if(isOnline()){
            binding.imageViewConectado.visibility = View.VISIBLE
            binding.imageViewDesconectado.visibility = View.GONE
        }else{
            binding.imageViewConectado.visibility = View.GONE
            binding.imageViewDesconectado.visibility = View.VISIBLE
        }

        binding.buttonCliente.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
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