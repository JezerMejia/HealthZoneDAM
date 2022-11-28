package com.jezerm.healthzone.ui.patient.home

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.jezerm.healthzone.MainActivity
import com.jezerm.healthzone.databinding.HomePatientHeaderBinding
import com.prolificinteractive.materialcalendarview.CalendarDay

val Int.dp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()
val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

class HomeHeaderAdapter : RecyclerView.Adapter<HomeHeaderAdapter.HeaderHolder>() {

    inner class HeaderHolder(private val binding: HomePatientHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun load() {
            binding.calendarView.addDecorator(
                EventDecorator(
                    arrayListOf(
                        CalendarDay.today(),
                        CalendarDay.from(2022, 11, 28),
                        CalendarDay.from(2022, 11, 30),
                        CalendarDay.from(2022, 11, 15),
                    )
                )
            )
            binding.calendarView.addDecorator(
                OneDayDecorator()
            )
            binding.calendarView.setOnDateChangedListener { widget, date, selected ->
                Snackbar.make(
                    binding.calendarView,
                    "${date.year}-${date.month}-${date.day}",
                    Snackbar.LENGTH_SHORT
                )
                    .setAnchorView(MainActivity.fabPrimary)
                    .show()
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