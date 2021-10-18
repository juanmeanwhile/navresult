package com.meanwhile.navigation.common

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.navigation.NavDirections

object IntentNavigation {
    fun getMainNavigationIntent(context: Context, directions: NavDirections, replaceDestinations: List<String> = emptyList()): Intent {
        return Intent().apply {
            component = ComponentName(context, MAIN_ACTIVITY_CLASS)

            putExtra(EXTRA_TARGET_DESTINATION, directions.actionId)
            putExtra(EXTRA_TARGET_ARGS, directions.arguments)
            putExtra(
                EXTRA_TARGET_REPLACERS,
                replaceDestinations.map {
                    context.resources.getIdentifier(it, "id", context.packageName)
                }.toIntArray()
            )
        }
    }

    // TODO Proguard this
    const val MAIN_ACTIVITY_CLASS = "com.meanwhile.navigationtest.MainActivity"

    const val EXTRA_TARGET_DESTINATION = "target_destination_id"
    const val EXTRA_TARGET_ARGS = "target_destination_args"
    const val EXTRA_TARGET_REPLACERS = "target_destination_replacers"
    const val EXTRA_HIDE_NAVIGATION_BAR = "hide_navigation_bar"
}

fun Bundle.hasNavigationDestination() = get(IntentNavigation.EXTRA_TARGET_DESTINATION) != null
