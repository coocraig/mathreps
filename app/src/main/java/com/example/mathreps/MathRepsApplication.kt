package com.example.mathreps

import android.app.Application
import com.example.mathreps.data.AttemptRoomDatabase

class MathRepsApplication: Application() {
    val database: AttemptRoomDatabase by lazy {AttemptRoomDatabase.getDatabase(this)}
}