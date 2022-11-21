package com.jezerm.healthzone.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
class Hospital(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "address") val address: String,
    @ColumnInfo(name = "telephone") val telephone: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "hours") val hours: String,
)