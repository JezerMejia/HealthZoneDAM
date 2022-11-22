package com.jezerm.healthzone.entities

import androidx.room.*
import com.jezerm.healthzone.utils.DateTime
import java.time.ZonedDateTime

@Entity
@TypeConverters(DateTime::class)
class Appointment(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "date") val date: ZonedDateTime,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "doctor_id") val doctorId: Int,
    @ColumnInfo(name = "patient_id") val patientId: Int,
)