package com.jezerm.healthzone.entities

import android.os.Parcelable
import androidx.room.*
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
class Prescription(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "user_id") var userId: Long?,
) : Parcelable

@Parcelize
data class PrescriptionWithMedicine(
    @Embedded val prescription: Prescription,
    @Relation(
        parentColumn = "id",
        entityColumn = "prescription_id"
    )
    val medicineList: List<Medicine>
) : Parcelable