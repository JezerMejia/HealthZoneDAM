package com.jezerm.healthzone.entities

import android.os.Parcelable
import androidx.room.*
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
class User(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "username") var username: String,
    @ColumnInfo(name = "password") var password: String,
    @ColumnInfo(name = "first_name") var firstName: String,
    @ColumnInfo(name = "middle_name") var middleName: String? = null,
    @ColumnInfo(name = "last_name") var lastName: String,
    @ColumnInfo(name = "telephone") var telephone: String? = null,
    @ColumnInfo(name = "is_doctor") var isDoctor: Boolean = true,

    // Doctor only
    @ColumnInfo(name = "hospital_id") var hospitalId: Int? = null,
    @ColumnInfo(name = "specialty") var specialty: String? = null,
    @ColumnInfo(name = "email") var email: String? = null,

    // Patient only
    @ColumnInfo(name = "age") var age: Int? = null,
    @ColumnInfo(name = "weight") var weight: Float? = null,
    @ColumnInfo(name = "height") var height: Float? = null,
    @ColumnInfo(name = "sex") var sex: Boolean? = null,
    @ColumnInfo(name = "conditions") var conditions: String? = null,

    ) : Parcelable {
    @Ignore
    var fullName: String = ""
        get() = "$firstName $lastName"

    override fun toString(): String {
        return "ID: $id, Name: $firstName $lastName, isDoctor: $isDoctor"
    }
}

@Parcelize
data class UserWithPrescription(
    @Embedded val user: User,
    @Relation(
        entity = Prescription::class,
        parentColumn = "id",
        entityColumn = "patient_id"
    )
    val prescriptionList: List<PrescriptionFull>
) : Parcelable
