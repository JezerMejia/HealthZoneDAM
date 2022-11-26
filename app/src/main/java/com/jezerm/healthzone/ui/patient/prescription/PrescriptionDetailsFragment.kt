package com.jezerm.healthzone.ui.patient.prescription

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.jezerm.healthzone.data.AppDatabase
import com.jezerm.healthzone.data.PrescriptionDAO
import com.jezerm.healthzone.databinding.FragmentPrescriptionDetailsBinding
import com.jezerm.healthzone.entities.PrescriptionFull
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class PrescriptionDetailsFragment : Fragment() {
    private lateinit var binding: FragmentPrescriptionDetailsBinding
    private lateinit var prescriptionFull: PrescriptionFull
    private lateinit var prescriptionDao: PrescriptionDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            prescriptionFull = it.getParcelable("prescription_full")!!
        }
        val db = AppDatabase.getInstance(requireContext())
        prescriptionDao = db.prescriptionDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPrescriptionDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val prescription = prescriptionFull.prescription
        binding.tvSubject.text = prescription.subject
        binding.tvDetails.text = prescription.details

        runBlocking {
            launch {
                val medicineList = prescriptionFull.medicineList

                binding.rcvMedicineList.layoutManager = LinearLayoutManager(requireContext())
                binding.rcvMedicineList.adapter = MedicineAdapter(ArrayList(medicineList))
            }
        }
    }
}