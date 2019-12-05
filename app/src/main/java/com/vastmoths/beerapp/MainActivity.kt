package com.vastmoths.beerapp

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.content.res.Configuration
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
import com.vastmoths.beerapp.broadcast.AirplaneModeReceiver

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    var serviceTask: BeerCountTask? = null
//    var listView: ListView = findViewById<ListView>(R.id.beerList)

//    var databaseCrud: DatabaseCRUD = DatabaseCRUD(applicationContext)

    val br: BroadcastReceiver = AirplaneModeReceiver()

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)

        // Checks the orientation of the screen
        if (newConfig?.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.fragment_home_vertical);
        } else if (newConfig?.orientation == Configuration.ORIENTATION_PORTRAIT){
            setContentView(R.layout.fragment_home);


        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Register broadcast receiver
//        val br: BroadcastReceiver = AirplaneModeReceiver()
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).apply {
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        }
        registerReceiver(br, filter)

//

        /// ##############################################

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
                R.id.nav_home, R.id.nav_create_beer, R.id.nav_list_beer
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.nav_list_beer) {
                fab.show()
            } else if (destination.id == R.id.nav_create_beer) {
                fab.hide()
            } else if (destination.id == R.id.nav_home) {
                fab.show()
            } else {
                fab.hide()
            }
        }
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

    override fun onPause() {
        super.onPause()
        unregisterReceiver(br)
    }

    fun endDrinkingOnClick(view: View) {

//            databaseCrud.insertBeer(Beer(0, "Tyske", 2, "jasne", "dupa"))


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
