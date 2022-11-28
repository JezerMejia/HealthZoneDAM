package com.jezerm.healthzone.ui.patient.Buttoms_nav_menu

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.jezerm.healthzone.databinding.AddButtomAppoitmentBinding

class Appoitment_add (
    private val onSubmitClicklistener: () -> Unit
): DialogFragment() {

    private lateinit var binding : AddButtomAppoitmentBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = AddButtomAppoitmentBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)

        binding.bAddQuantity.setOnClickListener(){
            onSubmitClicklistener.invoke()
        }

        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.setGravity(Gravity.CENTER)
        return dialog
    }

}