package com.jezerm.healthzone.entities

import android.os.Parcelable
import androidx.room.*
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
class Prescription(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "subject") var subject: String,
    @ColumnInfo(name = "patient_id") var patientId: Long?,
    @ColumnInfo(name = "doctor_id") var doctorId: Long?,
) : Parcelable

@Parcelize
data class PrescriptionFull(
    @Embedded val prescription: Prescription,
    @Relation(
        parentColumn = "id",
        entityColumn = "prescription_id"
    )
    val medicineList: List<Medicine>,
    @Relation(
        parentColumn = "patient_id",
        entityColumn = "id",
    )
    val patient: User?,
    @Relation(
        parentColumn = "doctor_id",
        entityColumn = "id",
    )
    val doctor: User?
) : Parcelable