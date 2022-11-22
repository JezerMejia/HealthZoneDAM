package com.jezerm.healthzone.ui.patient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jezerm.healthzone.databinding.FragmentAppointmentsPatientBinding
import com.jezerm.healthzone.entities.Appointment
import com.jezerm.healthzone.ui.patient.appointment.AppointmentAdapter
import com.jezerm.healthzone.utils.DateTime

class AppointmentsFragment : Fragment() {
    private lateinit var binding: FragmentAppointmentsPatientBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAppointmentsPatientBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val appointmentList = arrayListOf<Appointment>(
            Appointment(
                1,
                DateTime.now().plusHours(1),
                "Armando",
                0,
                0
            ),
            Appointment(
                2,
                DateTime.now().plusMinutes(2),
                "Armando",
                0,
                0
            ),
            Appointment(
                3,
                DateTime.format("2022-11-22 13:00 America/Managua"),
                "Armando",
                0,
                0
            ),
            Appointment(
                4,
                DateTime.format("2022-05-25 13:00 -06:00"),
                "Juan",
                0,
                0,
            ),
        )

        binding.rcvAppointmentList.layoutManager = LinearLayoutManager(requireContext())
        binding.rcvAppointmentList.adapter = AppointmentAdapter(appointmentList) {
            println("Clicked ${it.date}")
            val action = AppointmentsFragmentDirections.actionAppointmentsToDetails(it)
            findNavController().navigate(action)
        }
    }
}