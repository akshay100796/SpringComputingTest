package com.example.springtak.utils.room

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class RoomRepo(private val dao: RoomDao) {

    fun insert(table: Table) {
        dao.insert(table)
    }

    suspend fun getAll(): Flow<List<Table>?> = flow{
        try {
            emit(dao.getAll())
        }catch (ex: Exception) {
            emit(null)
        }
    }
}