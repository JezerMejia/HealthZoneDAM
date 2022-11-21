package com.jezerm.healthzone.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.jezerm.healthzone.entities.Hospital

@Dao
interface HospitalDAO {
    @Query("SELECT * FROM Hospital")
    suspend fun getAll(): List<Hospital>

    @Query("INSERT INTO Hospital" +
            "(name, address, telephone, email, hours) VALUES" +
            "('Hospital1', 'asdf', '88889999', 'a@a.com', '1111111')")
    suspend fun insertTestHospital()
}