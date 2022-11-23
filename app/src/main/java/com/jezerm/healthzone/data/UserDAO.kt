package com.jezerm.healthzone.data

import androidx.room.*
import com.jezerm.healthzone.entities.User

@Dao
interface UserDAO {
    @Query("SELECT * FROM User")
    suspend fun getAll(): List<User>

    @Query("SELECT * FROM User WHERE is_doctor = 'true'")
    suspend fun getAllDoctors(): List<User>

    @Query("SELECT * FROM User WHERE is_doctor = 'false'")
    suspend fun getAllPatients(): List<User>

//    @Query("SELECT * FROM Appointment WHERE id_doctor = :id")
//    suspend fun getDoctorsAppointments(): List<Appointment>

    @Query(
        "INSERT INTO User " +
                "(username, password, first_name, middle_name, last_name, telephone, is_doctor, age, weight, height, sex, conditions) VALUES" +
                "('ameza', '123', 'armando', 'alexander', 'meza', '77664604', 0, 21, 115, 164, 1, 'Diarrhea')"
    )
    suspend fun insertTestPatient()

    @Query(
        "INSERT INTO User " +
                "(username, password, first_name, middle_name, last_name, telephone, is_doctor, hospital_id, specialty) VALUES" +
                "('doctor', '123', 'jose', 'manuel', 'garcia', '88887777', 1, 1, 'cancer')"
    )
    suspend fun insertTestDoctor()

    @Insert
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * FROM User WHERE username = :username AND password = :password LIMIT 1")
    suspend fun login(username: String, password: String): List<User>

    @Query("SELECT * FROM User WHERE id = :id LIMIT 1")
    suspend fun getUserById(id: Int): List<User>

    @Query(
        "SELECT DISTINCT Patient.* FROM User as Doctor " +
                "INNER JOIN Appointment ON doctor_id = Doctor.id " +
                "INNER JOIN User as Patient ON Patient.id = Appointment.patient_id " +
                "WHERE Doctor.id = :doctorId"
    )
    suspend fun getDoctorsPatients(doctorId: Int): List<User>

    @Query(
        "INSERT INTO User" +
                "(username, password, first_name, middle_name, last_name, telephone, is_doctor, hospital_id, specialty) VALUES" +
                "(:username, :password, :firstName, :middleName, :lastName, :telephone, 1, :hospital, :specialty)"
    )
    suspend fun insertDoctor(
        username: String,
        password: String,
        firstName: String,
        middleName: String?,
        lastName: String,
        telephone: String?,
        hospital: Int,
        specialty: String
    )

    @Query(
        "INSERT INTO User" +
                "(username, password, first_name, middle_name, last_name, telephone, is_doctor, age, weight, height, sex, conditions) VALUES" +
                "(:username, :password, :firstName, :middleName, :lastName, :telephone, 1, :age, :weight, :height, :sex, :conditions)"
    )
    suspend fun insertPatient(
        username: String,
        password: String,
        firstName: String,
        middleName: String?,
        lastName: String,
        telephone: String?,
        age: Int,
        weight: Float,
        height: Float,
        sex: Boolean,
        conditions: String
    )
}