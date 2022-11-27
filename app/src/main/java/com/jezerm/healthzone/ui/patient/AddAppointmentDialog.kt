package com.jezerm.healthzone.ui.patient

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.DialogFragment
import com.jezerm.healthzone.databinding.AddAppointmentDialogBinding

class AddAppointmentDialog(
    private val onSubmitClicklistener: () -> Unit
) : DialogFragment() {

    private lateinit var binding: AddAppointmentDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = AddAppointmentDialogBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)

        binding.bAddQuantity.setOnClickListener {
            onSubmitClicklistener.invoke()
        }

        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.setGravity(Gravity.CENTER)
        return dialog
    }
}