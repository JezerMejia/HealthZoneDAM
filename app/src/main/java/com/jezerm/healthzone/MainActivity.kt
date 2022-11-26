package com.jezerm.healthzone

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.jezerm.healthzone.data.AppDatabase
import com.jezerm.healthzone.databinding.ActivityMainBinding
import com.jezerm.healthzone.entities.User
import com.jezerm.healthzone.ui.patient.AccountActivity
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private var requiresFab = true

    override fun onCreate(savedInstanceState: Bundle?) {
        appContext = applicationContext

        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        runBlocking {
            launch {
                val savedUser = getSavedUser()
                if (savedUser == null) {
                    val intent = Intent(appContext, LoginActivity::class.java).apply {
                        this.flags =
                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NO_HISTORY
                    }
                    startActivity(intent)
                    return@launch
                }
                user = savedUser
                init()
            }
        }
    }

    private fun init() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        if (user.isDoctor) {
            setupDoctorView()
        } else {
            setupPatientView()
        }

        binding.fabPrimary.addOnHideAnimationListener(object : AnimatorListener {
            override fun onAnimationStart(p0: Animator) {}
            override fun onAnimationEnd(p0: Animator) {
                if (!requiresFab) return
                binding.fabPrimary.show()
            }

            override fun onAnimationCancel(p0: Animator) {}
            override fun onAnimationRepeat(p0: Animator) {}
        })
    }

    private suspend fun getSavedUser(): User? {
        val sharedPref = getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE
        )
        val savedUserId = sharedPref.getInt("saved_user_id", -1)

        val db = AppDatabase.getInstance(applicationContext)
        val userDao = db.userDao()

        val userList = userDao.getUserById(savedUserId)
        if (userList.isEmpty()) return null

        return userList.first()
    }

    private fun setupPatientView() {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        navController.setGraph(R.navigation.nav_patient)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_prescriptions,
                R.id.navigation_maps,
                R.id.navigation_settings
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.bottomNavigation.setupWithNavController(navController)
        navController.addOnDestinationChangedListener(this)
    }

    private fun setupDoctorView() {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        navController.setGraph(R.navigation.nav_doctor)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_appointments,
                R.id.navigation_maps,
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.bottomNavigation.setupWithNavController(navController)
    }

    private fun openAccountView(): Boolean {
        val intent = Intent(this, AccountActivity::class.java).apply {
            this.putExtra("user", user)
        }
        startActivity(intent)
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.action_account -> openAccountView()
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    companion object {
        lateinit var appContext: Context
        lateinit var user: User
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        requiresFab = destination.id in setOf(
            R.id.navigation_home,
            R.id.navigation_prescriptions,
            R.id.navigation_settings,
        )
        if (requiresFab)
            binding.fabPrimary.show()
        binding.fabPrimary.hide()
        binding.fabPrimary.visibility = View.VISIBLE
    }
}