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
import com.jezerm.healthzone.databinding.FragmentRegisterPatientBinding
import com.jezerm.healthzone.entities.User
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class RegisterPatientFragment : Fragment() {
    private lateinit var binding: FragmentRegisterPatientBinding
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
        binding = FragmentRegisterPatientBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etUsername.setText(user.username)
        binding.btnRegister.setOnClickListener {
            addPatient()
        }
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun addPatient() {
        val age = binding.etAge.text.toString().toIntOrNull()
        val weight = binding.etWeight.text.toString().toFloatOrNull()
        val height = binding.etHeight.text.toString().toFloatOrNull()
        val sexSelection = binding.etSex.text.toString()
        val conditions = binding.etConditions.text.toString()
        var thereIsError = false

        if (age == null) {
            thereIsError = true
            binding.etAge.error = "Campo requerido"
        }
        if (weight == null) {
            thereIsError = true
            binding.etWeight.error = "Campo requerido"
        }
        if (height == null) {
            thereIsError = true
            binding.etHeight.error = "Campo requerido"
        }
        if (sexSelection.isBlank()) {
            thereIsError = true
            binding.etSex.error = "Campo requerido"
        }
        if (conditions.isBlank()) {
            thereIsError = true
            binding.etConditions.error = "Campo requerido"
        }

        if (thereIsError) return

        val sex = when (sexSelection) {
            resources.getString(R.string.sex_male) -> true
            else -> false
        }

        user.age = age
        user.weight = weight
        user.height = height
        user.sex = sex
        user.conditions = conditions

        runBlocking {
            launch {
                userDao.insertPatient(
                    user.username,
                    user.password,
                    user.firstName,
                    user.middleName,
                    user.lastName,
                    user.telephone,
                    user.age!!,
                    user.weight!!,
                    user.height!!,
                    user.sex!!,
                    user.conditions!!
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
                val action = RegisterPatientFragmentDirections.actionRegisterPatientToLogin()
                findNavController().navigate(action)
            }
            .show()
    }
}