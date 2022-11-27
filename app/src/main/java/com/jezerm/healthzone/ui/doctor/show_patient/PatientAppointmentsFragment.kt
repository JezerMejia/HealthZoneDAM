package com.jezerm.healthzone.ui.doctor.show_patient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jezerm.healthzone.databinding.FragmentShowPatientAppointmentsBinding
import com.jezerm.healthzone.entities.User

class PatientAppointmentsFragment : Fragment() {
    private lateinit var binding: FragmentShowPatientAppointmentsBinding
    private lateinit var patient: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            patient = it.getParcelable("patient")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowPatientAppointmentsBinding.inflate(inflater, container, false)
        return binding.root
    }
}