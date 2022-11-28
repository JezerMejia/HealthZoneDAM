package com.jezerm.healthzone.ui.patient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.transition.MaterialFadeThrough
import com.jezerm.healthzone.MainActivity
import com.jezerm.healthzone.R
import com.jezerm.healthzone.databinding.FragmentHomePatientBinding
import com.jezerm.healthzone.entities.Appointment
import com.jezerm.healthzone.ui.patient.appointment.AppointmentAdapter
import com.jezerm.healthzone.ui.patient.home.HomeHeaderAdapter
import com.jezerm.healthzone.utils.DateTime

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomePatientBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //param1 = it.getString(ARG_PARAM1)
        }

        val transition = MaterialFadeThrough()
        enterTransition = transition
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

        val adapter = ConcatAdapter(
            HomeHeaderAdapter(), AppointmentAdapter(
                arrayListOf(
                    Appointment(
                        1,
                        DateTime.now().plusHours(1),
                        "Armando",
                        0,
                        0
                    ),
                    Appointment(
                        1,
                        DateTime.now().plusHours(2),
                        "Armando",
                        0,
                        0
                    ),
                    Appointment(
                        1,
                        DateTime.now().plusHours(3),
                        "Armando",
                        0,
                        0
                    ),
                )
            )
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