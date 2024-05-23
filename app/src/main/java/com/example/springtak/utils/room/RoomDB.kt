package com.example.springtak.utils.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Table::class], version = 1, exportSchema = false)
abstract class RoomDB: RoomDatabase() {

    abstract fun dao(): RoomDao

    companion object {
        private var instance: RoomDB? = null
        fun getInstance(context: Context): RoomDB {
            synchronized(this) {
                if (instance == null) {
                    instance  = Room.databaseBuilder(
                        context,
                        RoomDB::class.java,
                        "SpringDB")
                        .fallbackToDestructiveMigration()
                        .setJournalMode(JournalMode.WRITE_AHEAD_LOGGING).build()
                }
                return instance as RoomDB
            }
        }
    }
}