package com.jezerm.healthzone.ui.patient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.jezerm.healthzone.databinding.FragmentAppointmentsPatientBinding
import com.jezerm.healthzone.entities.Appointment
import com.jezerm.healthzone.ui.patient.appointment.AppointmentAdapter
import com.jezerm.healthzone.utils.DateTime
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

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
                DateTime.format("2022-11-22 13:00 America/Managua"),
                "Armando"
            ),
            Appointment(
                DateTime.format("2022-05-25 13:00 -06:00"),
                "Juan"
            ),
        )

        val d = DateTime.format(2022, 11, 22, 16, 0, ZoneId.systemDefault())
        println(d)
        DateTime.toString(d)

        binding.rcvAppointmentList.layoutManager = LinearLayoutManager(requireContext())
        binding.rcvAppointmentList.adapter = AppointmentAdapter(appointmentList) {
            println("Clicked ${it.date}")
        }
    }
}