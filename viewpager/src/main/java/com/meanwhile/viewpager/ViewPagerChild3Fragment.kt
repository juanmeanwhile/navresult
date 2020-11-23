package com.meanwhile.viewpager

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

/**
 * Created on 23.11.20
 * @company arconsis IT-Solutions GmbH
 */
class ViewPagerChild3Fragment : Fragment(R.layout.fragment_viewpager_child3) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.button).setOnClickListener {
            findNavController().navigate(R.id.navigate_child3_to_nested)
        }
    }
}