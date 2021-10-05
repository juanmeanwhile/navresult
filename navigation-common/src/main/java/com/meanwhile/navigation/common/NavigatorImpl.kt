package com.meanwhile.navigation.common

import androidx.navigation.NavDirections
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class NavigatorImpl : Navigator {
    private val _navigationDestinations = MutableSharedFlow<Navigator.NavigationDestination>(
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
        extraBufferCapacity = 1
    )

    override val navigationDestinations: Flow<Navigator.NavigationDestination>
        get() = _navigationDestinations

    override fun navigateTo(directions: NavDirections, replaceDestinations: List<String>) {
        _navigationDestinations.tryEmit(Navigator.NavigationDestination(directions, replaceDestinations))
    }
}