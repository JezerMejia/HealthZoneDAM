package com.jezerm.healthzone.ui.patient

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.jezerm.healthzone.MainActivity
import com.jezerm.healthzone.R
import com.jezerm.healthzone.databinding.ActivityAccountPatientBinding
import com.jezerm.healthzone.entities.User
import com.jezerm.healthzone.ui.patient.AccountFragments.FirstFragment
import com.jezerm.healthzone.ui.patient.AccountFragments.SecondFragment

class AccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAccountPatientBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        val user = intent.getParcelableExtra<User>("user") ?: return

        binding = ActivityAccountPatientBinding.inflate(layoutInflater)
        setContentView(binding.root)
        start()
        setSupportActionBar(binding.toolbar)

        supportActionBar?.apply {
            title = user.fullName

            // show back button on toolbar
            // on back button press, it will navigate to parent activity
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    fun start() {
        val adapter = TabsFragmentAdapter(
            supportFragmentManager,
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_account, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_logout -> {
                logout()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun logout() {
        val sharedPref = getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE
        )
        sharedPref.edit().apply {
            remove("saved_user_id")
            apply()
        }
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}