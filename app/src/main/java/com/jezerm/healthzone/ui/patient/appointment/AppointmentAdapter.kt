package com.jezerm.healthzone.ui.patient.appointment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jezerm.healthzone.databinding.PatientAppointmentItemBinding
import com.jezerm.healthzone.entities.Appointment
import com.jezerm.healthzone.utils.DateTime

class AppointmentAdapter(
    var list: List<Appointment>,
    private val clickListener: (Appointment) -> Unit = { }
) : RecyclerView.Adapter<AppointmentAdapter.AppointmentHolder>() {

    inner class AppointmentHolder(private val binding: PatientAppointmentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun load(appointment: Appointment) {
            binding.tvDate.text = DateTime.toString(appointment.date)

            binding.cardContainer.setOnClickListener {
                clickListener(appointment)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentHolder {
        val binding = PatientAppointmentItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AppointmentHolder(binding)
    }

    override fun onBindViewHolder(holder: AppointmentHolder, position: Int) {
        holder.load(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}