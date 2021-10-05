package com.meanwhile.navigation.util

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner

/**
 * Shares a result with will be delivered to MainActivity. WFor some reason using parentFragmentManager does not deliver the result.
 */
fun Fragment.setMainFragmentResult(requestKey: String, bundle: Bundle) {
    requireActivity().supportFragmentManager.setFragmentResult(requestKey, bundle)
}



