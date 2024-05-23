package com.example.springtak.utils.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface RoomDao {

    @Transaction
    @Insert
    fun insert(table: Table)

    @Transaction
    @Query("SELECT * FROM `table`")
    fun getAll(): List<Table>
}