package com.example.mathreps

import androidx.lifecycle.*
import com.example.mathreps.data.Attempt
import com.example.mathreps.data.AttemptDao
import kotlinx.coroutines.launch

//Model that is exclusively used for accessing database type shit
class MathRepsViewModel(private val attemptDao: AttemptDao) : ViewModel() {


    //Inserts an attempt to the database
    private fun insertAttempt(attempt: Attempt){
        viewModelScope.launch {
            attemptDao.insert(attempt)
        }
    }

    private fun getNewAttemptEntry(wOL: String): Attempt {
        return Attempt(
            winOrLoss = wOL
        )
    }

    fun addNewAttempt(wOL : String) {
        val newAttempt = getNewAttemptEntry(wOL)
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