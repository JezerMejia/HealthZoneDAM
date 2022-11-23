package com.jezerm.healthzone

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.jezerm.healthzone.data.AppDatabase
import com.jezerm.healthzone.data.AppointmentDAO
import com.jezerm.healthzone.data.HospitalDAO
import com.jezerm.healthzone.data.UserDAO
import com.jezerm.healthzone.databinding.ActivityLoginBinding
import com.jezerm.healthzone.entities.User
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var userDao: UserDAO
    private lateinit var appointmentDao: AppointmentDAO
    private lateinit var hospitalDao: HospitalDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = AppDatabase.getInstance(applicationContext)
        userDao = db.userDao()
        appointmentDao = db.appointmentDao()
        hospitalDao = db.hospitalDao()
        runBlocking {
            launch {
                val users = userDao.getAll()
                val appointments = appointmentDao.getAll()
                val hospitals = hospitalDao.getAll()

                if (users.isEmpty()) {
                    userDao.insertTestPatient()
                    userDao.insertTestDoctor()
                    appointmentDao.insertTestAppointment()
                    hospitalDao.insertTestHospital()
                }
            }
        }

        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            val username: String = binding.etUsername.text.toString()
            val password: String = binding.etPassword.text.toString()
            runBlocking {
                launch {
                    val userList = userDao.login(username, password)
                    if (userList.isEmpty())
                        return@launch
                    setSavedUser(userList.first())
                    startActivity(intent)
                }
            }
        }
        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
                this.putExtra("logged_in", true)
                this.putExtra("doctor_mode", true)
            }
            startActivity(intent)
        }
    }

    private fun setSavedUser(user: User) {
        val sharedPref = getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE
        )
        sharedPref.edit().apply {
            putInt("saved_user_id", user.id)
            apply()
        }
    }
}