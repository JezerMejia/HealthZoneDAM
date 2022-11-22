package com.jezerm.healthzone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.WindowCompat
import com.jezerm.healthzone.data.AppDatabase
import com.jezerm.healthzone.data.AppointmentDAO
import com.jezerm.healthzone.data.HospitalDAO
import com.jezerm.healthzone.data.UserDAO
import com.jezerm.healthzone.databinding.ActivityLoginBinding
import com.jezerm.healthzone.entities.User
import kotlinx.coroutines.delay
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

                if(users.isEmpty()) {
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
                    val user: List<User> = userDao.login(username, password)
                    if (user.isEmpty())
                        return@launch
                    intent.putExtra("logged_in", true)
                    intent.putExtra("doctor_mode", user[0].isDoctor)
                    intent.putExtra("user", user[0])
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
}