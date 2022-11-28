package com.jezerm.healthzone.data

import androidx.room.*
import com.jezerm.healthzone.entities.Appointment
import com.jezerm.healthzone.entities.User

@Dao
interface AppointmentDAO {
    @Query("SELECT * FROM Appointment")
    suspend fun getAll(): List<Appointment>

    @Query(
        "INSERT INTO Appointment" +
                "(date, description, doctor_id, patient_id) VALUES" +
                "('2022-11-25 19:00 America/Managua', 'aaaaaa', 2, 1)"
    )
    suspend fun insertTestAppointment()

    @Query("SELECT * FROM Appointment WHERE patient_id = :patientId")
    suspend fun getAppointmentsOfPatient(patientId: Int): List<Appointment>

    suspend fun getAppointmentsOfPatient(patient: User): List<Appointment> {
        return getAppointmentsOfPatient(patient.id)
    }

    @Query("SELECT * FROM Appointment WHERE doctor_id = :doctorId")
    suspend fun getAppointmentsOfDoctor(doctorId: Int): List<Appointment>

    suspend fun getAppointmentsOfDoctor(doctor: User): List<Appointment> {
        return getAppointmentsOfDoctor(doctor.id)
    }
}