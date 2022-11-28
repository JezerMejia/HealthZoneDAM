package com.jezerm.healthzone.ui.patient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jezerm.healthzone.MainActivity
import com.jezerm.healthzone.data.AppDatabase
import com.jezerm.healthzone.data.AppointmentDAO
import com.jezerm.healthzone.databinding.FragmentAppointmentsPatientBinding
import com.jezerm.healthzone.entities.Appointment
import com.jezerm.healthzone.ui.patient.appointment.AppointmentAdapter
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

class AppointmentsFragment : Fragment() {
    private lateinit var binding: FragmentAppointmentsPatientBinding
    private var date: ZonedDateTime? = null
    private lateinit var appointmentDao: AppointmentDAO
    private var appointmentList = arrayListOf<Appointment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val dateLong = it.getLong("date")
            val i: Instant = Instant.ofEpochSecond(dateLong)
            date = ZonedDateTime.ofInstant(i, ZoneId.systemDefault())
            println("Date: $date")
        }

        val db = AppDatabase.getInstance(requireContext())
        appointmentDao = db.appointmentDao()

        runBlocking {
            launch {
                val list = appointmentDao.getAppointmentsOfPatient(MainActivity.user)
                appointmentList = if (date != null)
                    ArrayList(list.filter {
                        it.date.toLocalDate().isEqual(date?.toLocalDate())
                    })
                else
                    ArrayList(list)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAppointmentsPatientBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rcvAppointmentList.layoutManager = LinearLayoutManager(requireContext())
        binding.rcvAppointmentList.adapter = AppointmentAdapter(appointmentList) {
            println("Clicked ${it.date}")
            val action = AppointmentsFragmentDirections.actionAppointmentsToDetails(it)
            findNavController().navigate(action)
        }
    }
}