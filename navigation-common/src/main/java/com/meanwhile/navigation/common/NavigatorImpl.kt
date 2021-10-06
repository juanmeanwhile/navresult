package com.meanwhile.navigation.common

import androidx.navigation.NavDirections
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.receiveAsFlow

class NavigatorImpl : Navigator {
    private val _navigationDestinationsChannel =
        Channel<Navigator.NavigationDestination>(capacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    override val navigationDestinations: Flow<Navigator.NavigationDestination> = _navigationDestinationsChannel.receiveAsFlow()

    override fun navigateTo(directions: NavDirections, replaceDestinations: List<String>) {
        _navigationDestinationsChannel.trySend(Navigator.NavigationDestination(directions, replaceDestinations))
    }
}