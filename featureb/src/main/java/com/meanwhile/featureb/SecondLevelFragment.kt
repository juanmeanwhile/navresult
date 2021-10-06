package com.meanwhile.featureb

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.meanwhile.navigation.common.NavigationDestination

/**
 * A simple [Fragment] subclass.
 * Use the [SecondLevelFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondLevelFragment : Fragment(), NavigationDestination {
    override val args by SecondLevelNavDirections

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // You can get arguments that way:

        val param1 = args.param1
        val param2 = args.param2
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second_level, container, false)
    }
}