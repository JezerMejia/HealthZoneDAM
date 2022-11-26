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
import com.jezerm.healthzone.data.PrescriptionDAO
import com.jezerm.healthzone.databinding.FragmentPrescriptionsBinding
import com.jezerm.healthzone.ui.patient.prescription.PrescriptionAdapter
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * A simple [Fragment] subclass.
 * Use the [PrescriptionsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PrescriptionsFragment : Fragment() {
    private lateinit var binding: FragmentPrescriptionsBinding
    private lateinit var prescriptionDao: PrescriptionDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
        val db = AppDatabase.getInstance(requireContext())
        prescriptionDao = db.prescriptionDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPrescriptionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        runBlocking {
            launch {
                val prescriptionList = prescriptionDao.getPrescriptionsOfPatient(MainActivity.user)

                binding.rcvPrescriptionList.layoutManager = LinearLayoutManager(requireContext())
                binding.rcvPrescriptionList.adapter =
                    PrescriptionAdapter(ArrayList(prescriptionList)) {
                        val action =
                            PrescriptionsFragmentDirections.actionPrescriptionsToPrescriptionDetails(
                                it
                            )
                        findNavController().navigate(action)
                    }
            }
        }

    }
}