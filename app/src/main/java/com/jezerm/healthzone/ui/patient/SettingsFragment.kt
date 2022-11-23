package com.jezerm.healthzone.ui.patient

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.jezerm.healthzone.R
import com.jezerm.healthzone.databinding.FragmentAppointmentsPatientBinding
import com.jezerm.healthzone.databinding.FragmentHomePatientBinding
import com.jezerm.healthzone.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val options = resources.getStringArray(R.array.options)
        val arrayAdapter = ArrayAdapter(requireContext(),R.layout.dropdown_item, options)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)
        return binding.root
    }
}