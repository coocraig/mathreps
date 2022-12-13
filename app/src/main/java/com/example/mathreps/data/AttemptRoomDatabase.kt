package com.example.mathreps.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Attempt::class], version = 1, exportSchema = false)
abstract class AttemptRoomDatabase : RoomDatabase() {

    abstract fun attemptDao(): AttemptDao

    companion object {
        @Volatile
        private var INSTANCE: AttemptRoomDatabase? = null
        fun getDatabase(context: Context): AttemptRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AttemptRoomDatabase::class.java,
                    "attempt_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
