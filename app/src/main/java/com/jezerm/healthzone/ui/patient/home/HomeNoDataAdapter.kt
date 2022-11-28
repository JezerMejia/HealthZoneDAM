package com.jezerm.healthzone.ui.patient.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jezerm.healthzone.R
import com.jezerm.healthzone.databinding.HomePatientNoDataBinding

class HomeNoDataAdapter(
    val data: ArrayList<Any> = arrayListOf()
) : RecyclerView.Adapter<HomeNoDataAdapter.NoDataAdapter>() {
    inner class NoDataAdapter(private val binding: HomePatientNoDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun load() {
            val resources = binding.root.resources
            binding.tvDescription.text = resources.getString(R.string.no_appointments_description)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoDataAdapter {
        val binding = HomePatientNoDataBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NoDataAdapter(binding)
    }

    override fun onBindViewHolder(holder: NoDataAdapter, position: Int) {
        holder.load()
    }

    override fun getItemCount(): Int {
        return if (data.size > 0) {
            0
        } else {
            1
        }
    }
}