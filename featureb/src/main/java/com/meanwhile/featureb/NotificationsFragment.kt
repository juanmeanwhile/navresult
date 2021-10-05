package com.meanwhile.featureb

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.meanwhile.navigation.common.IntentNavigation
import com.meanwhile.navigation.common.common_destinations.NotificationsDirections

class NotificationsFragment : Fragment() {
    private val args by NotificationsDirections

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

        val text = args.text
        textView.text = text

        val button = root.findViewById<Button>(R.id.button3)
        button.setOnClickListener {
            startActivity(
                IntentNavigation.getMainNavigationIntent(requireContext(), SecondLevelNavDirections("param1", "param2"))
            )
        }

        return root
    }
}