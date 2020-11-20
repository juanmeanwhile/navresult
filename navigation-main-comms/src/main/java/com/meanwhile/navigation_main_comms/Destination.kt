package com.meanwhile.navigation_main_comms

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Represents an app screen which the user can navigate to
 */
sealed class Destination() : Parcelable {
    @Parcelize
    object Dashboard : Destination()

    @Parcelize
    class Notifications(val someData: String) : Destination()

    @Parcelize
    object Home: Destination()

    /**
     * Extends this when origin screen must be known.
     */
    abstract class DestinationWithOrigin(val origin: String) : Destination()

    @Parcelize
    object NotificationSecondLevel : DestinationWithOrigin("notifs")

}