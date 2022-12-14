package com.example.mathreps

import androidx.lifecycle.*
import com.example.mathreps.data.Attempt
import com.example.mathreps.data.AttemptDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//Model that is exclusively used for accessing database type shit
class MathRepsViewModel(private val attemptDao: AttemptDao) : ViewModel() {

    //Cache all the strings from the dao
    //var numberRepsAllTime = attemptDao.getWOL().asLiveData().value?.length

    //Determines if audio ought to be played or not
    private val _numberRepsAllTime = MutableLiveData<Int?>(0)
    val numberRepsAllTime: LiveData<Int?> = _numberRepsAllTime

    //sets number of reps for all time
    fun setATNOR()
    {
        _numberRepsAllTime.value = attemptDao.getWOL().asLiveData().value?.length
        if(_numberRepsAllTime.value == null)
        {
            _numberRepsAllTime.value = 45
        }
    }

    //Directly accesses values from the database for
    fun numberOfRepsAllTime(): Int {
        return attemptDao.getWOL().asLiveData().value?.length!!
    }

    fun numberOfNDiff(): Int {
        return attemptDao.getNotDiff().asLiveData().value?.length!!
    }

    fun numberOfSWDiff(): Int {
        return attemptDao.getSWDiff().asLiveData().value?.length!!
    }

    fun numberOfDiff(): Int {
        return attemptDao.getDiff().asLiveData().value?.length!!
    }

    fun numberOfVDiff(): Int {
        return attemptDao.getVDiff().asLiveData().value?.length!!
    }


    //Inserts an attempt to the database
    private fun insertAttempt(attempt: Attempt){
        viewModelScope.launch(Dispatchers.IO) {
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

    class MathRepsViewModelFactory(private val attemptDao: AttemptDao) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MathRepsViewModel::class.java)) {
                return MathRepsViewModel(attemptDao) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

}


}