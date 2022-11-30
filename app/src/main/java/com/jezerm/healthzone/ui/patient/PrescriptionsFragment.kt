package com.jezerm.healthzone.ui.patient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.transition.MaterialFadeThrough
import com.jezerm.healthzone.MainActivity
import com.jezerm.healthzone.R
import com.jezerm.healthzone.data.AppDatabase
import com.jezerm.healthzone.data.PrescriptionDAO
import com.jezerm.healthzone.databinding.FragmentPrescriptionsBinding
import com.jezerm.healthzone.entities.Prescription
import com.jezerm.healthzone.entities.PrescriptionFull
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
    private var prescriptionList = mutableListOf<PrescriptionFull>()
    private var completedList = mutableListOf<PrescriptionFull>()

    private val navigateListener: (PrescriptionFull) -> Unit = {
        val action = PrescriptionsFragmentDirections.actionPrescriptionsToPrescriptionDetails(it)
        findNavController().navigate(action)
    }
    private val completeListener: (PrescriptionFull, Int) -> Unit = { prescriptionFull, i ->
        completeItem(prescriptionFull, i)
    }
    private val deleteListener: (PrescriptionFull, Int) -> Unit = { prescriptionFull, i ->
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.title_delete_prescription_dialog)
            .setMessage(R.string.delete_prescription_message)
            .setNegativeButton(R.string.action_cancel) { _, _ ->
            }
            .setPositiveButton(R.string.action_delete) { _, _ ->
                removeItem(prescriptionFull.prescription, i)
            }
            .show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
        val transition = MaterialFadeThrough()
        enterTransition = transition

        val db = AppDatabase.getInstance(requireContext())
        prescriptionDao = db.prescriptionDao()

        runBlocking {
            launch {
                prescriptionList =
                    prescriptionDao.getPrescriptionsOfPatient(MainActivity.user).toMutableList()
                completedList =
                    prescriptionDao.getCompletedPrescriptionsOfPatient(MainActivity.user)
                        .toMutableList()
            }
        }
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

        binding.chipCompleted.setOnCheckedChangeListener { button, isChecked ->
            when (isChecked) {
                true -> showCompleted()
                false -> showUncompleted()
            }
            println("CHANGED: $isChecked")
        }

        showUncompleted()

        binding.rcvPrescriptionList.addOnScrollListener(MainActivity.onRecyclerViewScrollListener)
    }

    private fun removeItem(prescription: Prescription, position: Int) {
        runBlocking {
            launch {
                val adapter = binding.rcvPrescriptionList.adapter

                val toRemoveList = when (prescription.completed) {
                    true -> completedList
                    false -> prescriptionList
                }

                prescriptionDao.delete(prescription)
                toRemoveList.removeAt(position)
                adapter?.notifyItemRemoved(position)
                adapter?.notifyItemRangeChanged(position, toRemoveList.size)
            }
        }
    }

    private fun completeItem(prescriptionFull: PrescriptionFull, position: Int) {
        runBlocking {
            launch {
                val adapter = binding.rcvPrescriptionList.adapter
                val prescription = prescriptionFull.prescription
                val completed = prescription.completed
                prescriptionDao.completePrescription(prescription.id, !completed)
                prescription.completed = !completed

                val toRemoveList = when (completed) {
                    true -> completedList
                    false -> prescriptionList
                }
                val toAddList = when (completed) {
                    true -> prescriptionList
                    false -> completedList
                }
                toRemoveList.removeAt(position)
                toAddList.add(prescriptionFull)
                adapter?.notifyItemRemoved(position)
                adapter?.notifyItemRangeChanged(position, toRemoveList.size)
            }
        }
    }

    private fun showUncompleted() {
        binding.rcvPrescriptionList.layoutManager = LinearLayoutManager(requireContext())
        val adapter = PrescriptionAdapter(prescriptionList, navigateListener)
        adapter.completeListener = completeListener
        adapter.deleteListener = deleteListener

        binding.rcvPrescriptionList.adapter = adapter
    }

    private fun showCompleted() {
        binding.rcvPrescriptionList.layoutManager = LinearLayoutManager(requireContext())
        val adapter = PrescriptionAdapter(completedList, navigateListener)
        adapter.completeListener = completeListener
        adapter.deleteListener = deleteListener

        binding.rcvPrescriptionList.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        MainActivity.fabPrimaryIcon = R.drawable.ic_round_home_24
        MainActivity.fabPrimaryText = R.string.action_add_prescription
        MainActivity.fabPrimary.show()
        MainActivity.fabPrimary.setOnClickListener{
            AddprescriptionFrag.display(parentFragmentManager)

        }
    }
}