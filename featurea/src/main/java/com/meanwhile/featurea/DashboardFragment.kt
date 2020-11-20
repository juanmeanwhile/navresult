package com.meanwhile.featurea

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.meanwhile.featurea.DashboardFragment.Companion.RET_RESULT
import com.meanwhile.navigation_main_comms.Destination
import com.meanwhile.navigation_main_comms.NavEvent
import com.meanwhile.navigation_main_comms.NavigationViewModel
import com.meanwhile.navigation_main_comms.navigateToDestination
import com.meanwhile.navigation_main_comms.setMainFragmentResult
import kotlinx.android.parcel.Parcelize

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    private lateinit var navViewModel: NavigationViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        navViewModel = ViewModelProvider(requireActivity()).get(NavigationViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        root.findViewById<Button>(R.id.button).setOnClickListener {
            navigateToDestination(Destination.Notifications("Coming from Dashboard"))
        }

        root.findViewById<Button>(R.id.button2).setOnClickListener {
            setMainFragmentResult(REQ_KEY, bundleOf(RET_RESULT to DashBoardResult("XXX")))
        }

        setupObservers()

        return root
    }

    private fun setupObservers() {


        lifecycleScope.launchWhenStarted {
            navViewModel.navEventLiveData(NavEvent.InterestingMainEvent).observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), "Received ${it.toString()}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @Parcelize
    class DashBoardResult(val data: String) : Parcelable

    companion object {
        const val REQ_KEY = "dashboard"
        const val RET_RESULT = "result"
    }
}

fun Bundle.toDashBoardResult(): DashboardFragment.DashBoardResult {
    return getParcelable(RET_RESULT)!!
}


