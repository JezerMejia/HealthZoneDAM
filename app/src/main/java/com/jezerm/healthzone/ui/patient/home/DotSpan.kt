package com.jezerm.healthzone.ui.patient.home

import android.annotation.SuppressLint
import android.graphics.Canvas
import android.graphics.Paint
import android.text.style.LineBackgroundSpan
import androidx.appcompat.app.AppCompatDelegate
import com.jezerm.healthzone.MainActivity
import com.jezerm.healthzone.R

class DotSpan : LineBackgroundSpan {
    @SuppressLint("ResourceType")
    override fun drawBackground(
        canvas: Canvas,
        paint: Paint,
        left: Int,
        right: Int,
        top: Int,
        baseline: Int,
        bottom: Int,
        charSequence: CharSequence,
        start: Int,
        end: Int,
        lineNum: Int
    ) {
        val context = MainActivity.appContext
        val resources = context.resources

        val attrs = intArrayOf(com.google.android.material.R.attr.colorOnBackground)
        val ta = context.obtainStyledAttributes(R.style.Theme_HealthZone, attrs)

        val nightMode = AppCompatDelegate.getDefaultNightMode()
        val color = when (nightMode) {
            AppCompatDelegate.MODE_NIGHT_YES -> resources.getColor(R.color.md_theme_dark_onBackground)
            AppCompatDelegate.MODE_NIGHT_NO -> resources.getColor(R.color.md_theme_light_onBackground)
            else -> ta.getColor(0, R.color.md_theme_dark_onBackground)
        }
        val radius = 5f
        ta.recycle()

        val oldColor = paint.color
        if (color != 0) {
            paint.color = color
        }
        canvas.drawCircle(((left + right) / 2).toFloat(), bottom + radius*2, radius, paint)
        paint.color = oldColor
    }
}