package com.jezerm.healthzone.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.jezerm.healthzone.entities.PrescriptionFull
import com.jezerm.healthzone.entities.User

@Dao
interface PrescriptionDAO {
    @Transaction
    @Query("SELECT * FROM Prescription")
    suspend fun getAll(): List<PrescriptionFull>

    @Transaction
    @Query("SELECT * FROM Prescription WHERE completed = 1")
    suspend fun getAllCompleted(): List<PrescriptionFull>

    @Transaction
    @Query("SELECT * FROM Prescription WHERE patient_id = :patientId")
    suspend fun getPrescriptionsOfPatient(patientId: Int): List<PrescriptionFull>

    suspend fun getPrescriptionsOfPatient(patient: User): List<PrescriptionFull> {
        return getPrescriptionsOfPatient(patient.id)
    }

    @Transaction
    @Query("SELECT * FROM Prescription WHERE patient_id = :patientId AND completed = 1")
    suspend fun getCompletedPrescriptionsOfPatient(patientId: Int): List<PrescriptionFull>

    suspend fun getCompletedPrescriptionsOfPatient(patient: User): List<PrescriptionFull> {
        return getCompletedPrescriptionsOfPatient(patient.id)
    }

    @Query(
        "INSERT INTO Prescription " +
                "(subject, details, completed, patient_id, doctor_id) VALUES " +
                "(:subject, :details, :completed, :patientId, :doctorId)"
    )
    suspend fun insertPrescription(
        subject: String,
        details: String,
        completed: Boolean = false,
        patientId: Long?,
        doctorId: Long?
    )

    @Query(
        "INSERT INTO Prescription " +
                "(subject, details, completed, patient_id, doctor_id) VALUES " +
                "(:subject, :details, 0, :patientId, :doctorId)"
    )
    suspend fun insertPrescription(
        subject: String,
        details: String,
        patientId: Long?,
        doctorId: Long?
    )
}