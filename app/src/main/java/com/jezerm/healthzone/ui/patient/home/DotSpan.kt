package com.jezerm.healthzone.ui.patient.home

import android.graphics.Canvas
import android.graphics.Paint
import android.text.style.LineBackgroundSpan

class DotSpan : LineBackgroundSpan {
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
        val radius = 5f
        canvas.drawCircle(((left + right) / 2).toFloat(), bottom + radius * 2, radius, paint)
    }
}