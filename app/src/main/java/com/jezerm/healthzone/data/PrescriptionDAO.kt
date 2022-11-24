package com.jezerm.healthzone.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.jezerm.healthzone.entities.PrescriptionWithMedicine

@Dao
interface PrescriptionDAO {
    @Transaction
    @Query("SELECT * FROM Prescription")
    suspend fun getAll(): List<PrescriptionWithMedicine>
}