package com.jezerm.healthzone.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jezerm.healthzone.R
import com.jezerm.healthzone.databinding.FragmentRegisterPatientBinding
import com.jezerm.healthzone.entities.User

class RegisterPatientFragment : Fragment() {
    private lateinit var binding: FragmentRegisterPatientBinding
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            user = it.getParcelable("user")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterPatientBinding.inflate(inflater, container, false)
        return binding.root
    }
}