package com.meanwhile.viewpager

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

private const val VIEWPAGER_FRAGMENT = "VIEWPAGER_FRAGMENT"

class RootFragment : Fragment(R.layout.fragment_root) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val pos = arguments?.getInt("position") ?: 0
        toogleScreen(false, pos)
    }

    private fun toogleScreen(show: Boolean, position: Int) {
        if (show) {
            // currently not implemented
        } else {
            val fragment = childFragmentManager.findFragmentByTag(VIEWPAGER_FRAGMENT)
            if (fragment == null || !fragment.isAdded) {
                childFragmentManager.beginTransaction().replace(R.id.profile_root, ViewpagerFragment().apply {
                    generateArgs(position)
                }, VIEWPAGER_FRAGMENT).commit()
            }
        }
    }

    companion object {
        fun generateArgs(data: Int) = Bundle().apply {
            putInt("position", data)
        }
    }
}