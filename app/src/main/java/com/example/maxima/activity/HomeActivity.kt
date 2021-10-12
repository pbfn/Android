package com.example.maxima.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.maxima.NavGraphDirections
import com.example.maxima.R
import com.example.maxima.databinding.ActivityHomeBinding
import com.example.maxima.fragments.AlvarasFragment
import com.example.maxima.fragments.DadosFragment
import com.example.maxima.fragments.HistoricoFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)

        setContentView(binding.root)


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()


        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController)

        binding.bottomNavigation.setupWithNavController(navController)

        //setToolbar()
        //initBottomNavigation()

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


//    private fun setToolbar() {
//        val actionBar = supportActionBar
//        actionBar?.setDisplayShowCustomEnabled(true)
//        actionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
//        actionBar?.setCustomView(R.layout.action_bar_main)
//        actionBar?.setDisplayHomeAsUpEnabled(true)
//        actionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
//    }

//    private fun makeCurrentFragment(fragment: Fragment) =
//        supportFragmentManager.beginTransaction().apply {
//            replace(R.id.fl_container, fragment)
//            commit()
//        }
//
//    private fun initBottomNavigation() {
//        val dadosFragment = DadosFragment()
//        val alvarasFragment = AlvarasFragment()
//        val historicoFragment = HistoricoFragment()
//
//
//        makeCurrentFragment(dadosFragment)
//        binding.bottomNavigationView.setOnItemSelectedListener {
//            when (it.itemId) {
//                R.id.ic_dados -> makeCurrentFragment(dadosFragment)
//                R.id.ic_histo -> makeCurrentFragment(historicoFragment)
//                R.id.ic_alvaras -> makeCurrentFragment(alvarasFragment)
//            }
//            true
//        }
//
//        binding.bottomNavigationView.setOnItemReselectedListener {
//            when (it.itemId) {
//                R.id.ic_dados -> makeCurrentFragment(dadosFragment)
//                R.id.ic_histo -> makeCurrentFragment(historicoFragment)
//                R.id.ic_alvaras -> makeCurrentFragment(alvarasFragment)
//            }
//        }
//
//    }


}