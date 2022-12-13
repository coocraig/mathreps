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

}