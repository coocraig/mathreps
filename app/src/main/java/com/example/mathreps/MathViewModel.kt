package com.example.mathreps

import androidx.lifecycle.*
import com.example.mathreps.data.Attempt
import com.example.mathreps.data.AttemptDao
import kotlinx.coroutines.launch

class MathViewModel() : ViewModel(){
    //Determines if audio ought to be played or not
    private val _audio = MutableLiveData<Boolean?>(true)
    val audio: LiveData<Boolean?> = _audio

    //function to set the audio on or off for the game
    fun setAudio(bool: Boolean){
        _audio.value = bool
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
        } else {
            _resultText.value = "Wrong :("
        }
    }

}

