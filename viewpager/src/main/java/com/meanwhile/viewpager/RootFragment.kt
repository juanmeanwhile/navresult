package com.meanwhile.viewpager

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.meanwhile.navigation_main_comms.Destination
import com.meanwhile.navigation_main_comms.navigateToDestination

class RootFragment : Fragment(R.layout.fragment_root) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val pos = arguments?.getInt("position") ?: 0
        navigateToDestination(Destination.ViewPager(pos))
    }

    companion object {
        fun generateArgs(data: Int) = Bundle().apply {
            putInt("position", data)
        }
    }
}