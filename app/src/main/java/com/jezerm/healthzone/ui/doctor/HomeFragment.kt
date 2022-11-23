package com.jezerm.healthzone.ui.doctor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jezerm.healthzone.MainActivity
import com.jezerm.healthzone.data.AppDatabase
import com.jezerm.healthzone.databinding.FragmentHomeDoctorBinding
import com.jezerm.healthzone.entities.User
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeDoctorBinding
    private lateinit var doctor: User
    private lateinit var patients: List<User>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //param1 = it.getString(ARG_PARAM1)
        }
        doctor = MainActivity.user
        val db = AppDatabase.getInstance(requireContext())
        val userDao = db.userDao()
        runBlocking {
            launch {
                patients = userDao.getDoctorsPatients(doctor.id)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeDoctorBinding.inflate(inflater, container, false)
        binding.rcvPacientes.layoutManager = LinearLayoutManager(requireContext())
        binding.rcvPacientes.adapter = PatientAdapter(patients) { selectedPatient, i ->
            val action = HomeFragmentDirections.actionHomeToShowPatient(selectedPatient)
            findNavController().navigate(action)
        }
        return binding.root
    }
}