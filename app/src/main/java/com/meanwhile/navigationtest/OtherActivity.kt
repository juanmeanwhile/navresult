package com.meanwhile.navigationtest

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.meanwhile.navigation.common.Navigator
import com.meanwhile.navigation.common.common_destinations.NotificationsDirections

class OtherActivity: AppCompatActivity() {
    private val navigator = Navigator.inject() // This would be injected with hilt in production, allowing for test fakes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_other)

        findViewById<View>(R.id.button).setOnClickListener {
            navigator.navigateTo(NotificationsDirections("Opened From another activity"))
            finish()
        }
    }
}