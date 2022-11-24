package com.jezerm.healthzone.ui.patient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.*
import androidx.fragment.app.Fragment
import com.jezerm.healthzone.R
import com.jezerm.healthzone.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val switch = binding.switch1
        val autocomplete = binding.autoCompleteTextView

        val options = resources.getStringArray(R.array.options)
        binding.autoCompleteTextView.setSimpleItems(options)

        autocomplete.setOnItemClickListener { adapterView, view, i, l ->
            autocomplete.dismissDropDown()
            val item = options[i]
            when (item) {
                resources.getString(R.string.options_dark) -> changeTheme(MODE_NIGHT_YES)
                resources.getString(R.string.options_light) -> changeTheme(MODE_NIGHT_NO)
                resources.getString(R.string.options_automatic) -> changeTheme(
                    MODE_NIGHT_FOLLOW_SYSTEM
                )
            }
        }

        switch.setOnCheckedChangeListener { _, _ ->
        }
    }

    override fun onResume() {
        super.onResume()
        val options = resources.getStringArray(R.array.options)
        binding.autoCompleteTextView.setSimpleItems(options)
    }

    private fun changeTheme(mode: Int) {
        AppCompatDelegate.setDefaultNightMode(mode)
    }
}