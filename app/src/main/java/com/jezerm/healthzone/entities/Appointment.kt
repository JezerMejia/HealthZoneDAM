package com.jezerm.healthzone.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
class Appointment(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "doctor_id") val doctorId: String,
    @ColumnInfo(name = "patient_id") val patientId: String,
    @ColumnInfo(name = "description") val description: String,
)