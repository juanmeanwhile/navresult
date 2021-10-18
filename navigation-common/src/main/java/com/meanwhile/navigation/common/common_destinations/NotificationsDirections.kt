package com.meanwhile.navigation.common.common_destinations

import com.meanwhile.navigation.common.MeanwhileNavDirections
import com.meanwhile.navigation.common.NavDirectionsCompanion
import com.meanwhile.navigation.common.R
import kotlinx.parcelize.Parcelize

@Parcelize
class NotificationsDirections(val text: String): MeanwhileNavDirections() {
    override fun getActionId(): Int {
        return R.id.navigation_notifications
    }

    companion object: NavDirectionsCompanion<NotificationsDirections>() {
        override fun createDefault(): NotificationsDirections {
            return NotificationsDirections("Default text")
        }
    }
}