package com.meanwhile.featureb

import com.meanwhile.navigation.common.MeanwhileNavDirections
import com.meanwhile.navigation.common.NavDirectionsCompanion
import kotlinx.parcelize.Parcelize

@Parcelize
data class SecondLevelNavDirections(val param1: String, val param2: String): MeanwhileNavDirections() {
    override fun getActionId(): Int {
        return R.id.action_navigation_notifications_to_secondLevelFragment2
    }

    override val showBottomNavigationBar: Boolean
        get() = false

    companion object: NavDirectionsCompanion<SecondLevelNavDirections>()
}