package com.jezerm.healthzone.ui.patient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.transition.MaterialFadeThrough
import com.jezerm.healthzone.MainActivity
import com.jezerm.healthzone.R
import com.jezerm.healthzone.databinding.FragmentHomePatientBinding
import com.jezerm.healthzone.ui.patient.home.EventDecorator
import com.jezerm.healthzone.ui.patient.home.OneDayDecorator
import com.prolificinteractive.materialcalendarview.CalendarDay

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

        binding.calendarView.addDecorator(
            EventDecorator(
                arrayListOf(
                    CalendarDay.today(),
                    CalendarDay.from(2022, 11, 28),
                    CalendarDay.from(2022, 11, 30),
                    CalendarDay.from(2022, 11, 15),
                )
            )
        )
        binding.calendarView.addDecorator(
            OneDayDecorator()
        )
        binding.calendarView.setOnDateChangedListener { widget, date, selected ->
            Toast.makeText(
                requireContext(),
                "${date.year}-${date.month}-${date.day}",
                Toast.LENGTH_SHORT
            ).show()
        }
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