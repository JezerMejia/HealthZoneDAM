package com.jezerm.healthzone.ui.doctor.show_patient

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.jezerm.healthzone.R
import com.jezerm.healthzone.databinding.FragmentShowPatientBinding
import com.jezerm.healthzone.entities.User
import com.jezerm.healthzone.ui.patient.AccountFragments.FirstFragment
import com.jezerm.healthzone.ui.patient.AccountFragments.SecondFragment


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
        initData()
        start()
        setHasOptionsMenu(true)
        binding.toolbar.inflateMenu(R.menu.menu_empty)
        binding.toolbar.title = patient.fullName
        return binding.root
    }

    private fun initData() {
//        binding.tvName.text = patient.fullName
    }

    private fun start() {
        val adapter = TabsFragmentAdapter(
            requireActivity().supportFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )
        adapter.addItem(FirstFragment(), "Detalles")
        adapter.addItem(SecondFragment(), "Citas")

        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = adapter

        val tabLayout: TabLayout = binding.tabs
        tabLayout.setupWithViewPager(viewPager)
    }

    class TabsFragmentAdapter(fm: FragmentManager, behavior: Int) :
        FragmentPagerAdapter(fm, behavior) {

        private val listaFragment: MutableList<Fragment> = ArrayList()
        private val titleList: MutableList<String> = ArrayList()

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