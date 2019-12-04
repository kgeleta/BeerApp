package com.vastmoths.beerapp

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.vastmoths.beerapp.asynctask.BeerCountTask
import com.vastmoths.beerapp.broadcast.AirplainModeReceiver

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    var serviceTask: BeerCountTask? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Register broadcast receiver
        val br: BroadcastReceiver = AirplainModeReceiver()
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).apply {
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        }
        registerReceiver(br, filter)

        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)

        fab.setOnClickListener { view ->
            val navController = findNavController(R.id.nav_host_fragment)
            navController.navigate(R.id.nav_create_beer)
        }


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_create_beer, R.id.nav_list_beer,
                R.id.nav_detail_beer
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    fun onCancelCreateBeer(view: View) {
        val navController = findNavController(R.id.nav_host_fragment)
        navController.navigate(R.id.nav_home)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun endDrinkingOnClick(view: View) {
        if(serviceTask == null || serviceTask!!.isCancelled){
            Toast.makeText(applicationContext,
                "You cannot stop drinking if you did not even started!",
                Toast.LENGTH_SHORT).show()
        }else{
            serviceTask!!.cancel(true)
            serviceTask = null
            Toast.makeText(applicationContext,
                "You are drunk",
                Toast.LENGTH_SHORT).show()
        }
    }

    fun takeABeerOnClick(view: View) {
        if (serviceTask == null || serviceTask!!.isCancelled){
            serviceTask = BeerCountTask(applicationContext)
            serviceTask!!.execute()
        }
        Toast.makeText(applicationContext,
            "Take another beer",
            Toast.LENGTH_SHORT).show()
    }
}
