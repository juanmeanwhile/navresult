package com.meanwhile.featureb

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.meanwhile.navigation_main_comms.Destination
import com.meanwhile.navigation_main_comms.navigateToDestination

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
                ViewModelProvider(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)

        val text = arguments?.getString("flar")?:"Notifications fragment"
        textView.text = text

        val button = root.findViewById<Button>(R.id.button3)
        button.setOnClickListener {
            navigateToDestination(Destination.NotificationSecondLevel)
        }

        return root
    }

    companion object {
        fun generateArgs(data : String) = Bundle().apply {
            putString("flar", data)
        }
    }
}