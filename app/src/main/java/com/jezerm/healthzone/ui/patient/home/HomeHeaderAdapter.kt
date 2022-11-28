package com.jezerm.healthzone.ui.patient.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jezerm.healthzone.databinding.HomePatientHeaderBinding
import com.jezerm.healthzone.entities.Appointment
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener

class HomeHeaderAdapter(var list: ArrayList<Appointment>, var listener: OnDateSelectedListener) :
    RecyclerView.Adapter<HomeHeaderAdapter.HeaderHolder>() {

    inner class HeaderHolder(private val binding: HomePatientHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun load() {
            val calendarList: ArrayList<CalendarDay> = ArrayList(list.map {
                CalendarDay.from(
                    it.date.year,
                    it.date.monthValue,
                    it.date.dayOfMonth
                )
            })
            binding.calendarView.addDecorator(EventDecorator(calendarList))
            binding.calendarView.addDecorator(
                OneDayDecorator()
            )
            binding.calendarView.setOnDateChangedListener { widget, date, selected ->
                listener.onDateSelected(widget, date, selected)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderHolder {
        val binding = HomePatientHeaderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HeaderHolder(binding)
    }

    override fun onBindViewHolder(holder: HeaderHolder, position: Int) {
        holder.load()
    }

    override fun getItemCount(): Int {
        return 1
    }
}