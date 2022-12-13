package com.example.mathreps.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Attempt(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "difficulty")
    val difficulty: String,
    @ColumnInfo(name = "wol")
    val winOrLoss: String,
)