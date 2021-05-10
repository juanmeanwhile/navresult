package com.meanwhile.navigationtest

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.meanwhile.featurea.DashboardFragment
import com.meanwhile.featurea.toDashBoardResult
import com.meanwhile.featureb.NotificationsFragment
import com.meanwhile.navigation_main_comms.Destination
import com.meanwhile.navigation_main_comms.NavigationViewModel
import com.meanwhile.navigation_main_comms.setFragmentNavigationListener
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navViewModel : NavigationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navViewModel = ViewModelProvider(this).get(NavigationViewModel::class.java)
        setupListeners()
    }

    private fun setupListeners() {

        supportFragmentManager.setFragmentNavigationListener(this) { destination ->
            val navController = findNavController(R.id.nav_host_fragment)
            when (destination) {
                is Destination.Notifications -> {
                    val  args = NotificationsFragment.generateArgs(destination.someData)
                    navController.navigate(R.id.action_navigation_dashboard_to_navigation_notifications, args)
                }
                is Destination.Home -> {
                    navController.navigate(R.id.action_global_navigation_home)
                }
                is Destination.Dashboard -> {
                    navController.navigate(R.id.action_global_navigation_dashboard)
                }
                is Destination.DestinationWithOrigin -> {
                    val sharedView = findViewById<TextView>(R.id.sharedTitle)
                    val extras = FragmentNavigatorExtras(sharedView to destination.origin)

                    navController.navigate(R.id.action_navigation_notifications_to_secondLevelFragment2, null, null, extras)
                }
            }
        }

        supportFragmentManager.setFragmentResultListener(DashboardFragment.REQ_KEY, this) { key, bundle ->
            val result = bundle.toDashBoardResult()
            Toast.makeText(this, "Received: ${result.data}", Toast.LENGTH_LONG).show()
        }

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemReselectedListener {
            navViewModel.onSomethingInterestingHappen()
        }
    }
}