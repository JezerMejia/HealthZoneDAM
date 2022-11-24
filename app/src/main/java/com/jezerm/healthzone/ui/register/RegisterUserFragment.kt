package com.jezerm.healthzone.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jezerm.healthzone.R
import com.jezerm.healthzone.data.AppDatabase
import com.jezerm.healthzone.data.UserDAO
import com.jezerm.healthzone.databinding.FragmentRegisterUserBinding
import com.jezerm.healthzone.entities.User

class RegisterUserFragment : Fragment() {
    private lateinit var binding: FragmentRegisterUserBinding
    private lateinit var userDao: UserDAO
    private var isDoctorForm = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            isDoctorForm = it.getBoolean("doctor_form")
        }
        val db = AppDatabase.getInstance(requireContext())
        userDao = db.userDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvTitle.text =
            if (isDoctorForm) resources.getString(R.string.title_register_doctor) else
                resources.getString(R.string.title_register_patient)

        binding.btnRegister.setOnClickListener {
            val user = createUser() ?: return@setOnClickListener
            val action = if (isDoctorForm) {
                RegisterUserFragmentDirections.actionRegisterUserToRegisterDoctor(user)
            } else {
                RegisterUserFragmentDirections.actionRegisterUserToRegisterPatient(user)
            }
            findNavController().navigate(action)
        }
        binding.btnCancel.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun createUser(): User? {
        val username = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()
        val firstName = binding.etFirstName.text.toString()
        val middleName = binding.etMiddleName.text.toString()
        val lastName = binding.etLastname.text.toString()
        val cellphone = binding.etCellphone.text.toString()
        var thereIsError = false

        if (username.isBlank()) {
            thereIsError = true
            binding.etUsername.error = "Campo requerido"
        }
        if (password.isBlank()) {
            thereIsError = true
            binding.etPassword.error = "Campo requerido"
        }
        if (firstName.isBlank()) {
            thereIsError = true
            binding.etFirstName.error = "Campo requerido"
        }
        if (lastName.isBlank()) {
            thereIsError = true
            binding.etLastname.error = "Campo requerido"
        }
        if (thereIsError) return null

        return User(1, username, password, firstName, middleName, lastName, cellphone)
    }
}