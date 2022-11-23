package com.jezerm.healthzone.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
class User (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "middle_name") val middleName: String? = null,
    @ColumnInfo(name = "last_name") val lastName: String,
    @ColumnInfo(name = "telephone") val telephone: String? = null,
    @ColumnInfo(name = "is_doctor") val isDoctor: Boolean = true,

    // Doctor only
    @ColumnInfo(name = "hospital_id") val hospitalId: Int? = null,
    @ColumnInfo(name = "specialty") val specialty: String? = null,

    // Patient only
    @ColumnInfo(name = "age") val age: Int? = null,
    @ColumnInfo(name = "weight") val weight: Float? = null,
    @ColumnInfo(name = "height") val height: Float? = null,
    @ColumnInfo(name = "sex") val sex: Boolean? = null,
    @ColumnInfo(name = "conditions") val conditions: String? = null,

    ) : Parcelable {
    @Ignore var fullName: String = ""
        get() = "$firstName $lastName"

    override fun toString(): String {
        return "ID: $id, Name: $firstName $lastName, isDoctor: ${isDoctor.toString()}"
    }
}