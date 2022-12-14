package com.example.mathreps

import android.content.ClipData
import androidx.lifecycle.*
import com.example.mathreps.data.Attempt
import com.example.mathreps.data.AttemptDao
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

//Model that is exclusively used for accessing database type shit
class MathRepsViewModel(private val attemptDao: AttemptDao) : ViewModel() {

    // Cache all items form the database using LiveData.
    //private var allAttempt: LiveData<List<Attempt>> = attemptDao.getAttempts().asLiveData()


//    private val _numberRepsAllTime = MutableLiveData<Int?>(0)
//    val numberRepsAllTime: LiveData<Int?> = _numberRepsAllTime

    var numberRepsAllTime: Int = 0
    //sets number of reps for all time
//    fun setATNOR()
//    {
//        viewModelScope.launch(Dispatchers.IO) {
//            numberRepsAllTime = arrayOf(attemptDao.getAttempts().
//            if(numberRepsAllTime == null)
//            {
//                numberRepsAllTime = 45000
//            }
//        }
//    }

//    fun returnATNOR(): Int {
//        var amount = 0
//        viewModelScope.launch {
//            amount = attemptDao.getAttempts().toList().size
//        }
//        return amount
//    }

    suspend fun getNumberOfReps():Int{
        val deferred: Deferred<List<Attempt>> = viewModelScope.async {
            attemptDao.getAttempts()
        }
        return  deferred.await().size
    }

    suspend fun getNumberOfND():Int{
        val deferred: Deferred<List<String>> = viewModelScope.async {
            attemptDao.getNotDiff()
        }
        return  deferred.await().size
    }

    suspend fun getNumberOfSWD():Int{
        val deferred: Deferred<List<String>> = viewModelScope.async {
            attemptDao.getSWDiff()
        }
        return  deferred.await().size
    }

    suspend fun getNumberOfD():Int{
        val deferred: Deferred<List<String>> = viewModelScope.async {
            attemptDao.getDiff()
        }
        return  deferred.await().size
    }

    suspend fun getNumberOfVD():Int{
        val deferred: Deferred<List<String>> = viewModelScope.async {
            attemptDao.getVDiff()
        }
        return  deferred.await().size
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