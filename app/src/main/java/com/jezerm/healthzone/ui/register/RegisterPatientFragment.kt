package com.jezerm.healthzone.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jezerm.healthzone.data.AppDatabase
import com.jezerm.healthzone.data.UserDAO
import com.jezerm.healthzone.databinding.FragmentRegisterPatientBinding
import com.jezerm.healthzone.entities.User

class RegisterPatientFragment : Fragment() {
    private lateinit var binding: FragmentRegisterPatientBinding
    private lateinit var userDao: UserDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //param1 = it.getString(ARG_PARAM1)
        }
        val db = AppDatabase.getInstance(requireContext())
        userDao = db.userDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterPatientBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener {
            addPatient()
        }
        binding.btnCancel.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun addPatient() {
        val username = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()
        val firstName = binding.etFirstName.text.toString()
        val middleName = binding.etMiddleName.text.toString()
        val lastName = binding.etLastname.text.toString()
        val cellphone = binding.etCellphone.text.toString()

        if (username.isBlank()) {
            binding.etUsername.error = "Campo requerido"
            return
        }
        if (password.isBlank()) {
            binding.etPassword.error = "Campo requerido"
            return
        }
        if (firstName.isBlank()) {
            binding.etFirstName.error = "Campo requerido"
            return
        }
        if (lastName.isBlank()) {
            binding.etLastname.error = "Campo requerido"
            return
        }

        val user = User(-1, username, password, firstName, middleName, lastName, cellphone)
    }
}