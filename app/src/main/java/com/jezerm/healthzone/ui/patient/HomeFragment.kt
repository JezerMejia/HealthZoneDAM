package com.jezerm.healthzone.ui.patient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.transition.MaterialFadeThrough
import com.jezerm.healthzone.databinding.FragmentHomePatientBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomePatientBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //param1 = it.getString(ARG_PARAM1)
        }

        val transition = MaterialFadeThrough()
        enterTransition = transition
        exitTransition = transition
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomePatientBinding.inflate(inflater, container, false)
        return binding.root
    }
}