package com.example.springtak.utils.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table")
data class Table (
    @PrimaryKey(autoGenerate = true)
    val id:Int,

    val fname: String,
    val lname: String,
    val age: Int,
    val address : String,
    val city: String
)