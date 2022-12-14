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
    suspend fun getAttempts(): List<Attempt>

    //Inserts an attempt to the database
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(attempt: Attempt)


    @Query("SELECT wol FROM Attempt")
    suspend fun getWOL(): List<String>

    @Query("SELECT wol FROM Attempt WHERE wol = 'not_difficult'")
    suspend fun getNotDiff(): List<String>

    @Query("SELECT wol FROM Attempt WHERE wol = 'somewhat_difficult'")
    suspend fun getSWDiff(): List<String>

    @Query("SELECT wol FROM Attempt WHERE wol = 'difficult'")
    suspend fun getDiff(): List<String>

    @Query("SELECT wol FROM Attempt WHERE wol = 'very_difficult'")
    suspend fun getVDiff(): List<String>
}