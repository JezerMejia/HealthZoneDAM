package com.jezerm.healthzone.ui.patient

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.transition.MaterialFadeThrough
import com.jezerm.healthzone.MainActivity
import com.jezerm.healthzone.R
import com.jezerm.healthzone.databinding.FragmentHomePatientBinding
import com.jezerm.healthzone.ui.patient.Buttoms_nav_menu.Appoitment_add

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

    override fun onStart() {
        super.onStart()
        MainActivity.fabPrimaryIcon = R.drawable.ic_round_add_24
        MainActivity.fabPrimaryText = R.string.action_add_appointment
        MainActivity.fabPrimary.show()

        /*MainActivity.fabPrimary.setOnClickListener {
            val dialog = AddAppointmentDialog {
                println("Submit!")
            }
            dialog.show(parentFragmentManager, "addButton")
        }*/
        MainActivity.fabPrimary.setOnClickListener{
            Appoitment_add(onSubmitClicklistener = {

                /*Toast.makeText(this,"SEXOOOOOOOO", Toast.LENGTH_SHORT).show()*/
            }
            ).show(parentFragmentManager,"Test")
        }
    }
}