package com.jezerm.healthzone

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jezerm.healthzone.data.AppDatabase
import com.jezerm.healthzone.data.UserDAO
import com.jezerm.healthzone.databinding.FragmentLoginBinding
import com.jezerm.healthzone.entities.User
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var userDao: UserDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = AppDatabase.getInstance(requireContext())
        userDao = db.userDao()
        val appointmentDao = db.appointmentDao()
        val hospitalDao = db.hospitalDao()
        runBlocking {
            launch {
                val users = userDao.getAll()
                val appointments = appointmentDao.getAll()
                val hospitals = hospitalDao.getAll()

                if (users.isEmpty()) {
                    userDao.insertTestPatient()
                    userDao.insertTestDoctor()
                    appointmentDao.insertTestAppointment()
                    hospitalDao.insertTestHospital()
                }
            }
        }

        binding.btnLogin.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            val username: String = binding.etUsername.text.toString()
            val password: String = binding.etPassword.text.toString()
            runBlocking {
                launch {
                    val userList = userDao.login(username, password)
                    if (userList.isEmpty())
                        return@launch
                    setSavedUser(userList.first())
                    startActivity(intent)
                }
            }
        }

        binding.btnRegister.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginToRegisterPatient()
            findNavController().navigate(action)
        }
        binding.btnRegisterDoctor.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginToRegisterDoctor()
            findNavController().navigate(action)
        }
    }

    private fun setSavedUser(user: User) {
        val sharedPref = requireActivity().getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE
        )
        sharedPref.edit().apply {
            putInt("saved_user_id", user.id)
            apply()
        }
    }
}