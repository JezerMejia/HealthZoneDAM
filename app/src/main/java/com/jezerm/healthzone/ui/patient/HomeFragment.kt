package com.jezerm.healthzone.ui.patient

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jezerm.healthzone.R
import com.jezerm.healthzone.databinding.FragmentHomePatientBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomePatientBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomePatientBinding.inflate(inflater, container, false)
        return binding.root
    }
}