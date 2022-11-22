package com.jezerm.healthzone.ui.doctor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jezerm.healthzone.databinding.PatientCardBinding
import com.jezerm.healthzone.entities.User

class PatientAdapter(
    var list: List<User>,
    private val clickListener: (User, Int) -> Unit = { patient: User, i: Int -> }
) : RecyclerView.Adapter<PatientAdapter.PatientHolder>() {

    inner class PatientHolder(private val binding: PatientCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun load(patient: User) {
            with(binding) {
                tvName.text = patient.fullName
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientHolder {
        val binding = PatientCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PatientHolder(binding)
    }

    override fun onBindViewHolder(holder: PatientHolder, position: Int) {
        holder.load(list[position])
        holder.itemView.setOnClickListener {
            clickListener(list[position], position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}