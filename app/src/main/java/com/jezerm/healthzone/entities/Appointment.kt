package com.jezerm.healthzone.entities

import android.os.Parcelable
import androidx.room.*
import com.jezerm.healthzone.utils.DateTime
import kotlinx.parcelize.Parcelize
import java.time.ZonedDateTime

@Entity
@TypeConverters(DateTime::class)
@Parcelize
class Appointment(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "date") val date: ZonedDateTime,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "doctor_id") val doctorId: Int,
    @ColumnInfo(name = "patient_id") val patientId: Int,
) : Parcelable {
    override fun toString(): String {
        return "$id, $date, $description, $doctorId, $patientId"
    }
}