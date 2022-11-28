package com.jezerm.healthzone.ui.patient.home

import android.graphics.Typeface
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import java.time.LocalDate


/**
 * Decorate a day by making the text big and bold
 */
class OneDayDecorator : DayViewDecorator {
    private var date: CalendarDay?

    init {
        date = CalendarDay.today()
    }

    override fun shouldDecorate(day: CalendarDay): Boolean {
        return date != null && day == date
    }

    override fun decorate(view: DayViewFacade) {
        view.addSpan(StyleSpan(Typeface.BOLD))
        view.addSpan(RelativeSizeSpan(1.1f))
    }

    fun setDate(date: LocalDate) {
        this.date = CalendarDay.from(date.year, date.monthValue, date.dayOfMonth)
    }
}