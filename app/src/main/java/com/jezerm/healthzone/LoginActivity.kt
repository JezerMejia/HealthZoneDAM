package com.jezerm.healthzone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jezerm.healthzone.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
                this.putExtra("logged_in", true)
                this.putExtra("doctor_mode", false)
            }
            startActivity(intent)
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