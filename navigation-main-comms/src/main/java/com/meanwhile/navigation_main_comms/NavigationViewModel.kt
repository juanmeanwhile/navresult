package com.meanwhile.navigation_main_comms

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

/**
 * Shared viewModel to be used when parent-child main navigation communication is needed
 */
class NavigationViewModel() : ViewModel() {

    private val eventsFlow = MutableStateFlow<NavEvent>(NavEvent.Empty)

    private var dirtyFieldForDemo: Boolean = false // don't do this at home

    /**
     * Action from MainActivity to be propagated to tab fragments which might be interested
     */
    @ExperimentalCoroutinesApi
    fun onSomethingInterestingHappen() {
        // every subsequent call we send different values to check that Ui receives the filtered events
        val event = if (dirtyFieldForDemo) NavEvent.InterestingMainEvent else NavEvent.NotInterestingMainEvent
        eventsFlow.value = event
        dirtyFieldForDemo = !dirtyFieldForDemo
    }

    /**
     * Get a single event LiveData which only received the specified event types. Once the event it read it is consumed and replaced again for Empty.
     */
    @ExperimentalCoroutinesApi
    fun navEventLiveData(vararg events: NavEvent): LiveData<NavEvent> {
        return eventsFlow
                .filter {
                    it in events
                }
                .map { event ->
                    eventsFlow.value = NavEvent.Empty
                    event
                }.asLiveData()
    }
}

sealed class NavEvent {
    object Empty : NavEvent()
    object InterestingMainEvent : NavEvent()
    object NotInterestingMainEvent: NavEvent()
}