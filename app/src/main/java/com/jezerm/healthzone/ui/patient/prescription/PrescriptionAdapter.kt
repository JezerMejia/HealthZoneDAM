package com.jezerm.healthzone.ui.patient.prescription

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jezerm.healthzone.R
import com.jezerm.healthzone.databinding.PatientPrescriptionItemBinding
import com.jezerm.healthzone.entities.PrescriptionFull

class PrescriptionAdapter(
    var list: List<PrescriptionFull>,
    private val clickListener: (PrescriptionFull) -> Unit = {}
) : RecyclerView.Adapter<PrescriptionAdapter.PrescriptionHolder>() {

    var completeListener: (PrescriptionFull, Int) -> Unit = { p, i -> }
    var deleteListener: (PrescriptionFull, Int) -> Unit = { p, i -> }

    inner class PrescriptionHolder(private val binding: PatientPrescriptionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun load(prescriptionFull: PrescriptionFull) {
            val resources = binding.root.resources
            val prescription = prescriptionFull.prescription
            binding.tvSubject.text = prescription.subject
            binding.tvDetails.text = prescription.details
            binding.tvQuantity.text =
                resources.getString(
                    R.string.prescription_size_quantity,
                    prescriptionFull.medicineList.size
                )

            when (prescription.completed) {
                true -> binding.btnComplete.text = resources.getString(R.string.action_uncomplete)
                false -> binding.btnComplete.text = resources.getString(R.string.action_complete)
            }

            binding.cardContainer.setOnClickListener {
                clickListener(prescriptionFull)
            }
            binding.btnComplete.setOnClickListener {
                completeListener(prescriptionFull, adapterPosition)
            }
            binding.btnDelete.setOnClickListener {
                deleteListener(prescriptionFull, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrescriptionHolder {
        val binding = PatientPrescriptionItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PrescriptionHolder(binding)
    }

    override fun onBindViewHolder(holder: PrescriptionHolder, position: Int) {
        holder.load(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
