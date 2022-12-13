package com.example.mathreps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mathreps.data.Attempt
import com.example.mathreps.data.AttemptDao
import kotlinx.coroutines.launch

class MathRepsViewModel(private val attemptDao: AttemptDao) : ViewModel() {

    //Inserts an attempt to the database
    private fun insertAttempt(attempt: Attempt){
        viewModelScope.launch {
            attemptDao.insert(attempt)
        }
    }

    private fun getNewAttemptEntry(diff: String, wOL: String): Attempt {
        return Attempt(
            difficulty = diff,
            winOrLoss = wOL
        )
    }

    fun addNewAttempt(diff: String, wOL : String) {
        val newAttempt = getNewAttemptEntry(diff, wOL)
        insertAttempt(newAttempt)
    }

}

class MathRepsViewModelFactory(private val attemptDao: AttemptDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MathRepsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MathRepsViewModel(attemptDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}