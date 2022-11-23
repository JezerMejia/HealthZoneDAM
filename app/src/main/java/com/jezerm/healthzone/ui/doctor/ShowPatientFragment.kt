package com.jezerm.healthzone.ui.doctor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jezerm.healthzone.databinding.FragmentShowPatientBinding
import com.jezerm.healthzone.entities.User


class ShowPatientFragment : Fragment() {

    private lateinit var binding: FragmentShowPatientBinding
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
        binding = FragmentShowPatientBinding.inflate(inflater, container, false)
        initData()
        return binding.root
    }

    private fun initData() {
        binding.tvName.text = patient.fullName
    }
}