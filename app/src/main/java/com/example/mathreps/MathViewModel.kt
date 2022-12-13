package com.example.mathreps

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MathViewModel : ViewModel(){
    //Determines if audio ought to be played or not
    private val _audio = MutableLiveData<Boolean?>()
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

    //Randomly sets the numbers
    fun setNumbers(largeRange: IntRange?) {
        _smallNumber.value = (2..9).random()
        _largeNumber.value = largeRange?.random()
    }

}