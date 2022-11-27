package com.jezerm.healthzone.ui.doctor.show_patient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jezerm.healthzone.databinding.FragmentShowPatientDetailsBinding
import com.jezerm.healthzone.entities.User

class PatientDetailsFragment : Fragment() {
    private lateinit var binding: FragmentShowPatientDetailsBinding
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

        binding = FragmentShowPatientDetailsBinding.inflate(inflater, container, false)
        initData()
        return binding.root
    }

    private fun initData() {
        binding.tvAge.text = "${patient.age} años"
        binding.tvHeight.text = "${patient.height} cm"
        binding.tvWeight.text = "${patient.weight} lbs"
        binding.tvSex.text = if (patient.sex!!) "Masculino" else "Femenino"
        binding.tvConditions.text = patient.conditions.toString()
    }
}
