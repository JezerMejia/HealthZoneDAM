package com.jezerm.healthzone.ui.doctor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jezerm.healthzone.databinding.FragmentShowPatientBinding
import com.jezerm.healthzone.entities.User


class ShowPatientFragment : Fragment() {

    private lateinit var binding: FragmentShowPatientBinding
    private lateinit var patient: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            patient = it.getSerializable("patient") as User
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentShowPatientBinding.inflate(inflater, container, false)
        initData()
        return binding.root
    }

    private fun initData() {
        binding.tvName.text = patient.fullName
    }

    companion object {
        @JvmStatic fun newInstance(selectedPatient: User) =
                ShowPatientFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable("patient", selectedPatient)
                    }
                }
    }
}