package com.meanwhile.navigation.common

import androidx.navigation.NavDirections
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

interface Navigator {
    val navigationDestinations: Flow<NavigationDestination>
    fun navigateTo(directions: NavDirections, replaceDestinations: List<String> = emptyList())

    companion object {
        private val navigator = NavigatorImpl()

        fun inject(): Navigator {
            return navigator
        }
    }

    data class NavigationDestination(val directions: NavDirections, val replaceDestinations: List<String>)
}

