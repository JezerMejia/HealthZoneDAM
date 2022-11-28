package com.jezerm.healthzone.ui.patient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.transition.MaterialFadeThrough
import com.jezerm.healthzone.MainActivity
import com.jezerm.healthzone.R
import com.jezerm.healthzone.data.AppDatabase
import com.jezerm.healthzone.data.AppointmentDAO
import com.jezerm.healthzone.databinding.FragmentHomePatientBinding
import com.jezerm.healthzone.entities.Appointment
import com.jezerm.healthzone.ui.patient.appointment.AppointmentAdapter
import com.jezerm.healthzone.ui.patient.home.HomeHeaderAdapter
import com.jezerm.healthzone.ui.patient.home.HomeNoDataAdapter
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.ZoneId
import java.time.ZonedDateTime

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomePatientBinding
    private lateinit var appointmentDao: AppointmentDAO
    private var appointmentList = arrayListOf<Appointment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //param1 = it.getString(ARG_PARAM1)
        }

        val transition = MaterialFadeThrough()
        enterTransition = transition

        val db = AppDatabase.getInstance(requireContext())
        appointmentDao = db.appointmentDao()

        runBlocking {
            launch {
                appointmentList =
                    ArrayList(appointmentDao.getAppointmentsOfPatient(MainActivity.user))
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomePatientBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val headerAdapter = HomeHeaderAdapter(appointmentList) { widget, date, selected ->
            val zonedDateTime =
                ZonedDateTime.of(
                    date.year, date.month, date.day, 0, 0, 0, 0, ZoneId.systemDefault()
                )
            appointmentList.find {
                it.date.toLocalDate().isEqual(zonedDateTime.toLocalDate())
            } ?: return@HomeHeaderAdapter

            val action = HomeFragmentDirections.actionHomeToAppointments(
                zonedDateTime.toEpochSecond()
            )
            findNavController().navigate(action)
        }

        val nextAppointmentList = ArrayList(appointmentList.filter {
            it.date.isAfter(ZonedDateTime.now().minusHours(1))
        })
        val adapter = ConcatAdapter(
            headerAdapter,
            AppointmentAdapter(nextAppointmentList),
            HomeNoDataAdapter(nextAppointmentList as ArrayList<Any>)
        )

        binding.rcvAppointmentList.layoutManager = LinearLayoutManager(requireContext())
        binding.rcvAppointmentList.adapter = adapter
        binding.rcvAppointmentList.addOnScrollListener(MainActivity.onRecyclerViewScrollListener)
    }

    override fun onStart() {
        MainActivity.fabPrimaryIcon = R.drawable.ic_round_add_24
        MainActivity.fabPrimaryText = R.string.action_add_appointment
        MainActivity.fabPrimary.show()

        MainActivity.fabPrimary.setOnClickListener {
            val dialog = AddAppointmentDialog {
                println("Submit!")
            }
            dialog.show(parentFragmentManager, "addButton")
        }

        super.onStart()
    }
}