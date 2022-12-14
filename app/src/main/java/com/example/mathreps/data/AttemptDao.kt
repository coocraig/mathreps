package com.example.mathreps.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface AttemptDao {
    //query for whole table (Will be used to calculate statistics after the list of attempts has been generated)
    @Query("SELECT * from Attempt")
    fun getAttempts(): Flow<List<Attempt>>

    //Inserts an attempt to the database
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(attempt: Attempt)


    @Query("SELECT wol FROM Attempt")
    fun getWOL(): Flow<String>

    @Query("SELECT wol FROM Attempt WHERE wol = 'not_difficult'")
    fun getNotDiff(): Flow<String>

    @Query("SELECT wol FROM Attempt WHERE wol = 'somewhat_difficult'")
    fun getSWDiff(): Flow<String>

    @Query("SELECT wol FROM Attempt WHERE wol = 'difficult'")
    fun getDiff(): Flow<String>

    @Query("SELECT wol FROM Attempt WHERE wol = 'very_difficult'")
    fun getVDiff(): Flow<String>
}