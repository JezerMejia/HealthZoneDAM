package com.jezerm.healthzone.ui.doctor.show_patient

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.jezerm.healthzone.R
import com.jezerm.healthzone.databinding.FragmentShowPatientBinding
import com.jezerm.healthzone.entities.User


class ShowPatientFragment : Fragment() {

    private lateinit var binding: FragmentShowPatientBinding
    private lateinit var patient: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            patient = it.getParcelable("patient")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowPatientBinding.inflate(inflater, container, false)
        start()
        setToolbar()
        return binding.root
    }


    private fun setToolbar() {
        binding.toolbar.title = patient.fullName
        (activity as AppCompatActivity?)!!.supportActionBar?.hide()
        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity?)!!.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onDestroyView() {
        val toolbar: Toolbar = (activity as AppCompatActivity?)!!.findViewById(R.id.toolbar)
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)
        (activity as AppCompatActivity?)!!.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        toolbar.title = "Inicio"
        (activity as AppCompatActivity?)!!.supportActionBar?.show()
        super.onDestroyView()
    }

    private fun start() {
        val adapter = TabsFragmentAdapter(
            childFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )
        val args = Bundle()
        args.putParcelable("patient", patient)

        val details: Fragment = PatientDetailsFragment()
        val appointments:Fragment = PatientAppointmentsFragment()
        details.arguments = args
        appointments.arguments = args

        adapter.addItem(details, "Detalles")
        adapter.addItem(appointments, "Citas")

        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = adapter

        val tabLayout: TabLayout = binding.tabs
        tabLayout.setupWithViewPager(viewPager)
    }

    class TabsFragmentAdapter(fm: FragmentManager, behavior: Int) :
        FragmentPagerAdapter(fm, behavior) {

        private val listaFragment: ArrayList<Fragment> = ArrayList()
        private val titleList: ArrayList<String> = ArrayList()

        fun addItem(fragment: Fragment, titulo: String) {
            listaFragment.add(fragment)
            titleList.add(titulo)
        }

        override fun getCount(): Int {
            return listaFragment.size
        }

        override fun getItem(position: Int): Fragment {
            return listaFragment[position]
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titleList[position]
        }

    }

}