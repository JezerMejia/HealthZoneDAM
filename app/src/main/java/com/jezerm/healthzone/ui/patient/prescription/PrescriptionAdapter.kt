package com.jezerm.healthzone.ui.patient.prescription

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jezerm.healthzone.R
import com.jezerm.healthzone.databinding.PatientPrescriptionItemBinding
import com.jezerm.healthzone.entities.PrescriptionFull

class PrescriptionAdapter(
    var list: ArrayList<PrescriptionFull>,
    private val clickListener: (PrescriptionFull) -> Unit = { }
) : RecyclerView.Adapter<PrescriptionAdapter.PrescriptionHolder>() {

    inner class PrescriptionHolder(private val binding: PatientPrescriptionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun load(prescriptionFull: PrescriptionFull) {
            val resources = binding.root.resources
            binding.tvSubject.text = prescriptionFull.prescription.subject
            binding.tvDetails.text = prescriptionFull.prescription.details
            binding.tvQuantity.text =
                resources.getString(
                    R.string.prescription_size_quantity,
                    prescriptionFull.medicineList.size
                )

            binding.cardContainer.setOnClickListener {
                clickListener(prescriptionFull)
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
