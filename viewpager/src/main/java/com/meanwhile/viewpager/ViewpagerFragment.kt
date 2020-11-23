package com.meanwhile.viewpager

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

/**
 * Created on 23.11.20
 * @company arconsis IT-Solutions GmbH
 */
class ViewpagerFragment : Fragment(R.layout.fragment_viewpager) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // TabLayout
        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)
        // ViewPager2
        val viewPager = view.findViewById<ViewPager2>(R.id.viewPager)

        val fragments = listOf(ViewPagerChild1Fragment(),
            ViewPagerChild2Fragment(),
            ViewPagerChild3Fragment())

        viewPager.adapter = ViewPagerAdapter(fragments, childFragmentManager, viewLifecycleOwner.lifecycle)

        // Bind tabs and viewpager
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "First"
                1 -> "Second"
                2 -> "Third"
                else -> throw error("error")
            }
        }.attach()

        val page = arguments?.getInt("page") ?: 0
        viewPager.currentItem = page
    }

    companion object {
        fun generateArgs(data: Int) = Bundle().apply {
            putInt("page", data)
        }
    }
}