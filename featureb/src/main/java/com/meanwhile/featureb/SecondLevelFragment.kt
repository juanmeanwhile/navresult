package com.meanwhile.featureb

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.transition.Fade
import androidx.transition.TransitionInflater

/**
 * A simple [Fragment] subclass.
 * Use the [SecondLevelFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondLevelFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        enterTransition = Fade()
        exitTransition = Fade()
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(R.transition.move)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second_level, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                SecondLevelFragment().apply {
                    arguments = Bundle().apply {}
                }
    }
}