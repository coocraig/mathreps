package com.example.mathreps

import androidx.lifecycle.*
import com.example.mathreps.data.Attempt
import com.example.mathreps.data.AttemptDao
import kotlinx.coroutines.launch
import com.example.mathreps.MathRepsViewModel

class MathViewModel() : ViewModel(){

    private val _numberRepsAllTime = MutableLiveData<Int?>()
    val numberRepsAllTime: LiveData<Int?> = _numberRepsAllTime

    fun setATNORZ(newVal: Int) {
        _numberRepsAllTime.value = newVal
    }

    //Determines if audio ought to be played or not
    private val _audio = MutableLiveData<Boolean?>(true)
    val audio: LiveData<Boolean?> = _audio

    //function to set the audio on or off for the game
    fun setAudio(bool: Boolean){
        _audio.value = bool
    }

    //Determines if audio ought to be played or not
    private val _showUserRating = MutableLiveData<Boolean?>(true)
    val showUserRating: LiveData<Boolean?> = _showUserRating


    //function to set the audio on or off for the game
    fun setShowUserRating(bool: Boolean){
        _showUserRating.value = bool
    }


    //The amount of reps in current sesssion
    private val _seshReps = MutableLiveData<Int?>(0)
    val seshReps: LiveData<Int?> = _seshReps

    //The amount of reps in current sesssion
    private val _seshCorrect = MutableLiveData<Int?>(0)
    val seshCorrect: LiveData<Int?> = _seshCorrect

    //The amount of reps in current sesssion
    private val _seshCorrectString = MutableLiveData<String?>("0")
    val seshCorrectString: LiveData<Int?> = _seshCorrect

    fun setSeshStrings(){
        _seshCorrectString.value = _seshCorrect.value.toString()
    }

    //The different ranges
    private final val easyRange = (2..10)
    private final val mediumRange = (11..100)
    private final val hardRange = (101..999)

    //range to be used for the random number
    private val _range = MutableLiveData<IntRange?>((1..999))
    val range: LiveData<IntRange?> = _range

    fun setRange(diff: String) {
        if(diff.equals("easy")){
            _range.value = easyRange
        }
        if(diff.equals("medium")){
            _range.value = mediumRange
        }
        if(diff.equals("hard")){
            _range.value = hardRange
        }
    }

    //The larger number (number on top)
    private val _largeNumber = MutableLiveData<Int?>()
    val largeNumber: LiveData<Int?> = _largeNumber

    //The number (number on bottom)
    private val _smallNumber = MutableLiveData<Int?>()
    val smallNumber: LiveData<Int?> = _smallNumber

    //The number (number on bottom)
    private val _actualAnswer = MutableLiveData<Int?>()
    val actualAnswer: LiveData<Int?> = _actualAnswer

    //Randomly sets the numbers
    fun setNumbers(largeRange: IntRange?) {
        _smallNumber.value = (2..9).random()
        _largeNumber.value = largeRange?.random()
        _actualAnswer.value = _smallNumber.value!! * _largeNumber.value!!
        _seshReps.value = _seshReps.value!! + 1
    }

    //the answer given by the user
    private val _answer = MutableLiveData<String?>()
    val answer: LiveData<String?> = _answer

    //Can be used to set this answer
    fun setAnswer(response: String){
        _answer.value = response
    }

    //The string for if the answer is correct or incorrect
    private val _resultText = MutableLiveData<String?>()
    val resultText: LiveData<String?> = _resultText

    //The value of if the answer is correct or not
    private val _isCorrect = MutableLiveData<Boolean?>()
    val isCorrect: LiveData<Boolean?> = _isCorrect

    fun setResultText() {
        if(_answer.value?.toInt() == _actualAnswer.value?.toInt()){
            _resultText.value = "Correct!"
            _seshCorrect.value = _seshCorrect.value!! + 1
            setSeshStrings()
        } else {
            _resultText.value = "Wrong :("
        }
    }


}

