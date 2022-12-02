package com.fintold.grandtask.uI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.fintold.grandtask.MainActivityViewModel
import com.fintold.grandtask.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModel<MainActivityViewModel>()
    private val navController by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_activity_fragment_view) as NavHostFragment
        navHostFragment.navController
    }
    private val appBarConfiguration by lazy { AppBarConfiguration(navController.graph) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActionBarWithNavController(navController,appBarConfiguration)
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }
}