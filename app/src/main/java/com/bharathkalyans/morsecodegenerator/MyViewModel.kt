package com.bharathkalyans.morsecodegenerator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {


    private val _name = MutableLiveData<String>()
    val name: LiveData<String>
        get() = _name


    fun updateName(newName: String){
        _name.value = newName
    }



    /* private lateinit var timer: CountDownTimer
     private val _seconds = MutableLiveData<Int>()

     val seconds: LiveData<Int>
         get() = _seconds

     val finished =  MutableLiveData<Boolean>()
     val timerValue = MutableLiveData<Long>()

     fun startTimer()  {
         timer = object : CountDownTimer(timerValue.value!!.toLong(), 1000) {
             override fun onTick(millisUntilFinished: Long) {
                 val sec = millisUntilFinished / 1000
                 _seconds.value = sec.toInt()
             }

             override fun onFinish() {
                 finished.value = true
             }

         }.start()
     }

     fun stopTimer() {
         timer.cancel()
     }*/

}