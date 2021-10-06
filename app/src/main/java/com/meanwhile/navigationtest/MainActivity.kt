package com.meanwhile.navigationtest

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.meanwhile.featurea.DashboardFragment
import com.meanwhile.featurea.toDashBoardResult
import com.meanwhile.navigation.common.NavigationDestination
import com.meanwhile.navigation.common.Navigator
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val navigator = Navigator.inject() // This would be injected with hilt in production, allowing for test fakes

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

        lifecycleScope.launch {
            navigator.navigationDestinations.flowWithLifecycle(lifecycle).collect(::handleNavigationTarget)
        }

        findNavController(R.id.nav_host_fragment).addOnDestinationChangedListener { _, _, _ -> onNavigationDestinationChanged() }
    }

    private fun handleNavigationTarget(navigationDestination: Navigator.NavigationDestination) {
        val controller = findNavController(R.id.nav_host_fragment)

        val currentScreen = controller.currentDestination?.id
        val navOptions = NavOptions.Builder()

        for (screenToReplaceString in navigationDestination.replaceDestinations) {
            val screenToReplace = resources.getIdentifier(screenToReplaceString, "id", packageName)

            if (screenToReplace != 0 && currentScreen == screenToReplace) {
                navOptions.setPopUpTo(screenToReplace, true)
            }
        }

        val destination = navigationDestination.directions.actionId
        if (currentScreen != destination) {
            controller.navigate(destination, navigationDestination.directions.arguments, navOptions.build())
        }
    }

    private fun onNavigationDestinationChanged() {
        val navHost = supportFragmentManager.primaryNavigationFragment ?: return
        navHost.childFragmentManager.executePendingTransactions()
        val currentFragment = navHost.childFragmentManager.primaryNavigationFragment
        val currentArgs = (currentFragment as? NavigationDestination)?.args

        findViewById<View>(R.id.nav_view).isVisible = currentArgs?.showBottomNavigationBar != false
    }
}