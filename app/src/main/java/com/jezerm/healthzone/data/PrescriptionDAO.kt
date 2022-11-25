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
    @Query("SELECT * FROM Prescription WHERE patient_id = :patientId")
    suspend fun getPrescriptionsOfPatient(patientId: Int): List<PrescriptionFull>

    suspend fun getPrescriptionsOfPatient(patient: User): List<PrescriptionFull> {
        return getPrescriptionsOfPatient(patient.id)
    }

    @Query("INSERT INTO Prescription (subject, patient_id, doctor_id) VALUES (:subject, :patientId, :doctorId)")
    suspend fun insertPrescription(subject: String, patientId: Long?, doctorId: Long?)
}