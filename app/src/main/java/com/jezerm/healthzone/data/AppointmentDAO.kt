package com.jezerm.healthzone.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.jezerm.healthzone.entities.Appointment

@Dao
interface AppointmentDAO {
    @Query("SELECT * FROM Appointment")
    suspend fun getAll(): List<Appointment>

    @Query("INSERT INTO Appointment" +
            "(date, doctor_id, patient_id, description) VALUES" +
            "('2022-11-25', 2, 1, 'aaaaaa')")
    suspend fun insertTestAppointment()
}