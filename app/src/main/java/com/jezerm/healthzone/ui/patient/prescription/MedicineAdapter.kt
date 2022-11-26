package com.jezerm.healthzone.ui.patient.prescription

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jezerm.healthzone.R
import com.jezerm.healthzone.databinding.PatientMedicineItemBinding
import com.jezerm.healthzone.entities.Medicine

class MedicineAdapter(
    var list: ArrayList<Medicine>,
    private val clickListener: (Medicine) -> Unit = { }
) : RecyclerView.Adapter<MedicineAdapter.MedicineHolder>() {

    inner class MedicineHolder(private val binding: PatientMedicineItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun load(medicine: Medicine) {
            val resources = binding.root.resources
            binding.tvName.text = medicine.name
            binding.tvQuantity.text = resources.getString(R.string.medicine_quantity, medicine.quantity)

            binding.cardContainer.setOnClickListener {
                clickListener(medicine)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineHolder {
        val binding = PatientMedicineItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MedicineHolder(binding)
    }

    override fun onBindViewHolder(holder: MedicineHolder, position: Int) {
        holder.load(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
