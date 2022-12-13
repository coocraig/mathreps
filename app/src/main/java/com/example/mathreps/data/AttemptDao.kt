package com.example.mathreps.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface AttemptDao {
    //query for whole table (Will be used to calculate statistics after the list of attempts has been generated)
    @Query("SELECT * from attempt")
    fun getAttempts(): Flow<List<Attempt>>

    //Inserts an attempt to the database
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(attempt: Attempt)
}