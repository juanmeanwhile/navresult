package com.meanwhile.navigationtest

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.meanwhile.featurea.DashboardFragment
import com.meanwhile.featurea.toDashBoardResult
import com.meanwhile.navigation.common.IntentNavigation

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        setupListeners()
    }

    private fun setupListeners() {
        supportFragmentManager.setFragmentResultListener(DashboardFragment.REQ_KEY, this) { key, bundle ->
            val result = bundle.toDashBoardResult()
            Toast.makeText(this, "Received: ${result.data}", Toast.LENGTH_LONG).show()
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        if (intent.hasExtra(IntentNavigation.EXTRA_TARGET_DESTINATION)) {
            handleNavigationTarget(intent)
        }
    }

    private fun handleNavigationTarget(intent: Intent) {
        val controller = findNavController(R.id.nav_host_fragment)
        val replacers = intent.getIntArrayExtra(IntentNavigation.EXTRA_TARGET_REPLACERS) ?: intArrayOf()

        val currentScreen = controller.currentDestination?.id
        val navOptions = NavOptions.Builder()

        for (screenToReplace in replacers) {
            if (currentScreen == screenToReplace) {
                navOptions.setPopUpTo(screenToReplace, true)
            }
        }

        val destination = intent.getIntExtra(IntentNavigation.EXTRA_TARGET_DESTINATION, 0)

        if (currentScreen != destination) {
            controller.navigate(destination, intent.getBundleExtra(IntentNavigation.EXTRA_TARGET_ARGS), navOptions.build())
        }
    }
}