package com.jezerm.healthzone.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
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

    @Query("INSERT INTO User " +
            "(username, password, first_name, middle_name, last_name, telephone, is_doctor, age, weight, height, sex, conditions) VALUES" +
            "('ameza', '123', 'armando', 'alexander', 'meza', '77664604', 'false', 21, 115, 164, 'true', 'Diarrea')")
    suspend fun insertTestPatient()

    @Query("INSERT INTO User " +
            "(username, password, first_name, middle_name, last_name, telephone, is_doctor, hospital_id, specialty) VALUES" +
            "('doctor', '123', 'jose', 'manuel', 'garcia', '88887777', 'true', 1, 'cancer')")
    suspend fun insertTestDoctor()

    @Insert
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Query("DELETE FROM User WHERE id =:id")
    suspend fun delete(id:Int)

    @Query("SELECT * FROM User WHERE username = :username AND password = :password")
    suspend fun getById(username: String, password: String): List<User>
}