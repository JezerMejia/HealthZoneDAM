package com.jezerm.healthzone.ui.doctor.show_patient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jezerm.healthzone.MainActivity
import com.jezerm.healthzone.R
import com.jezerm.healthzone.data.AppDatabase
import com.jezerm.healthzone.databinding.FragmentShowPatientAppointmentsBinding
import com.jezerm.healthzone.entities.Appointment
import com.jezerm.healthzone.entities.User
import com.jezerm.healthzone.ui.doctor.HomeFragmentDirections
import com.jezerm.healthzone.ui.doctor.PatientAdapter
import com.jezerm.healthzone.ui.patient.AppointmentsFragmentDirections
import com.jezerm.healthzone.ui.patient.appointment.AppointmentAdapter
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class PatientAppointmentsFragment : Fragment() {
    private lateinit var binding: FragmentShowPatientAppointmentsBinding
    private lateinit var patient: User
    private lateinit var doctor: User
    private lateinit var appointments: List<Appointment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            patient = it.getParcelable("patient")!!
        }
        doctor = MainActivity.user
        val db = AppDatabase.getInstance(requireContext())
        val appointmentDao = db.appointmentDao()
        runBlocking {
            launch {
                appointments = appointmentDao.getByDoctorAndPatient(doctor.id, patient.id)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowPatientAppointmentsBinding.inflate(inflater, container, false)
        initData()
        return binding.root
    }

    private fun initData() {
        binding.rcvAppointments.layoutManager = LinearLayoutManager(requireContext())
        binding.rcvAppointments.adapter = AppointmentAdapter(appointments) {

            val action = ShowPatientFragmentDirections.appointmentDetailsFragment(it)
            val fragment = requireParentFragment()
            fragment.findNavController().navigate(action)
        }
    }
}