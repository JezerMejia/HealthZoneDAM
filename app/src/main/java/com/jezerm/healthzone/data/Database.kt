package com.jezerm.healthzone.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jezerm.healthzone.entities.User
import com.jezerm.healthzone.entities.Appointment
import com.jezerm.healthzone.entities.Hospital

@Database(entities = [User::class, Appointment::class, Hospital::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDAO
    abstract fun appointmentDao(): AppointmentDAO
    abstract fun hospitalDao(): HospitalDAO

    companion object {
        private var instance: AppDatabase? = null

        fun getInstance(context: Context) : AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context, AppDatabase::class.java, "healthzone").build()
            }
            return instance!!
        }
    }
}