package com.jezerm.healthzone.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.jezerm.healthzone.R
import com.jezerm.healthzone.data.AppDatabase
import com.jezerm.healthzone.data.UserDAO
import com.jezerm.healthzone.databinding.FragmentRegisterMedicoBinding
import com.jezerm.healthzone.entities.User
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class RegisterMedicoFragment : Fragment() {
    private lateinit var binding: FragmentRegisterMedicoBinding
    private lateinit var userDao: UserDAO
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            user = it.getParcelable("user")!!
        }
        val db = AppDatabase.getInstance(requireContext())
        userDao = db.userDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterMedicoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etUsername.setText(user.username)
        binding.btnRegister.setOnClickListener {
            addDoctor()
        }
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun addDoctor() {
        val email = binding.etEmail.text.toString()
        val specialty = binding.etSpecialty.text.toString()
        val hospital = binding.etWorkplace.text.toString()
        var thereIsError = false

        if (specialty.isBlank()) {
            thereIsError = true
            binding.etSpecialty.error = "Campo requerido"
        }
        if (email.isBlank()) {
            thereIsError = true
            binding.etEmail.error = "Campo requerido"
        }
        if (hospital.isBlank()) {
            thereIsError = true
            binding.etWorkplace.error = "Campo requerido"
        }

        if (thereIsError) return

        user.specialty = specialty
        user.hospitalId = 1
        user.email = email

        runBlocking {
            launch {
                userDao.insertDoctor(
                    user.username,
                    user.password,
                    user.firstName,
                    user.middleName,
                    user.lastName,
                    user.telephone,
                    user.hospitalId!!,
                    user.specialty!!,
                    user.email!!
                )

                showConfirmDialog()
            }
        }
    }

    private fun showConfirmDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(resources.getString(R.string.register_succeeded))
            .setMessage(resources.getString(R.string.register_succeeded_message))
            .setPositiveButton(resources.getString(R.string.action_accept)) { _, _ ->
            }
            .setOnDismissListener {
                val action = RegisterMedicoFragmentDirections.actionRegisterDoctorToLogin()
                findNavController().navigate(action)
            }
            .show()
    }
}