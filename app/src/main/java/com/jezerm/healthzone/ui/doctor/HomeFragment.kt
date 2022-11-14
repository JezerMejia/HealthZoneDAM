package com.jezerm.healthzone.ui.doctor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jezerm.healthzone.R
import com.jezerm.healthzone.databinding.FragmentHomeDoctorBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeDoctorBinding

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
        binding = FragmentHomeDoctorBinding.inflate(inflater, container, false)
        return binding.root
    }
}