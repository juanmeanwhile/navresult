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
import com.meanwhile.navigation.common.IntentNavigation
import com.meanwhile.navigation.common.common_destinations.NotificationsDirections
import com.meanwhile.navigation.util.setMainFragmentResult
import kotlinx.parcelize.Parcelize

class DashboardFragment : Fragment() {


    private lateinit var dashboardViewModel: DashboardViewModel


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        root.findViewById<Button>(R.id.button).setOnClickListener {
            startActivity(
                IntentNavigation.getMainNavigationIntent(
                    requireContext(),
                    NotificationsDirections("Coming from Dashboard")
                )
            )
        }

        root.findViewById<Button>(R.id.button2).setOnClickListener {
            setMainFragmentResult(REQ_KEY, bundleOf(RET_RESULT to DashBoardResult("XXX")))
        }

        return root
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


