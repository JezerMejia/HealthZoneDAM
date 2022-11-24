package com.jezerm.healthzone.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
class Medicine(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "prescription_id") var prescriptionId: Long?,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "quantity") var quantity: Long,
) : Parcelable