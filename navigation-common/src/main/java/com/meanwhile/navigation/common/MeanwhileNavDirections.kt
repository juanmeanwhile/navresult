package com.meanwhile.navigation.common

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

abstract class MeanwhileNavDirections : NavDirections, Parcelable {
    override fun getArguments(): Bundle {
        return Bundle().apply {
            putParcelable(ARG_DIRECTIONS, this@MeanwhileNavDirections)
        }
    }

    open val showBottomNavigationBar: Boolean
        get() = true
}

abstract class NavDirectionsCompanion<T : MeanwhileNavDirections> {
    fun fromBundle(bundle: Bundle): T? {
        return bundle.getParcelable<T>(ARG_DIRECTIONS)
    }

    open fun createDefault(): T {
        throw IllegalStateException("Fragment was not started using Navigator. If this fragment is supposed to be started on its own, you need to override " +
            "createDefault in its NavDirections's companion.")
    }

    operator fun provideDelegate(thisRef: Fragment, property: KProperty<*>): ReadOnlyProperty<Fragment, T> {
        return object : ReadOnlyProperty<Fragment, T> {
            private var prevValue: T? = null

            override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
                prevValue?.let { return it }

                val arguments = thisRef.arguments
                val value = arguments?.getParcelable(ARG_DIRECTIONS) ?: createDefault()

                prevValue = value

                return value
            }
        }
    }
}

private const val ARG_DIRECTIONS = "Directions"
