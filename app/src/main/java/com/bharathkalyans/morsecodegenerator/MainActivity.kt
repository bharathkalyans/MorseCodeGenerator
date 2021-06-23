package com.bharathkalyans.morsecodegenerator

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bharathkalyans.morsecodegenerator.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        val viewModel = ViewModelProvider(this).get(MyViewModel::class.java)


        viewModel.name.observe(this, { text ->

            val chars = text.toCharArray()
            val result = convertToMorseCode(chars)
            tvMorseCode.text = result

        })

        etPlainText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                viewModel.updateName(s.toString())
            }
        })

        /*viewModel.seconds.observe(this, {
            tvSeconds.text = it.toString()
        })

        viewModel.finished.observe(this, {
            if (it) {
                Toast.makeText(this, "Completed", Toast.LENGTH_SHORT).show()
            }
        })

        btnStart.setOnClickListener {
            if (etTotalSeconds.text.isEmpty() || etTotalSeconds.text.length < 4) {
                Toast.makeText(this, "Please Enter Correct Values!", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.timerValue.value = etTotalSeconds.text.toString().toLong()
                viewModel.startTimer()
            }
        }
        btnStop.setOnClickListener {
            tvSeconds.text = "0"
            viewModel.stopTimer()
        }*/
    }

    private fun convertToMorseCode(charArray: CharArray): String {
        val morseCodeMap = mapOf(
            "A" to "*_",
            "B" to "_***",
            "C" to "_*_*",
            "D" to "_**",
            "E" to "*",
            "F" to "**_*",
            "G" to "__*",
            "H" to "****",
            "I" to "**",
            "J" to "*___",
            "K" to "_*_",
            "L" to "*_**",
            "M" to "__",
            "N" to "_*",
            "O" to "___",
            "P" to "*__*",
            "Q" to "__*_",
            "R" to "*_*",
            "S" to "***",
            "T" to "_",
            "U" to "**_",
            "V" to "***_",
            "W" to "*__",
            "X" to "_**_",
            "Y" to "_*__",
            "Z" to "__**",
            "1" to "*____",
            "2" to "**___",
            "3" to "***__",
            "4" to "****_",
            "5" to "*****",
            "6" to "_****",
            "7" to "__***",
            "8" to "___**",
            "9" to "____*",
            "0" to "_____",
            "." to "*_*_*_",
            "," to "__**__",
            ":" to "___***",
            "?" to "**__**",
            "," to "*____*",
            "(" to "_*__*_",
            ")" to "_*__*_",
            "-" to "_****_",
            "\"" to "*_**_*",
            " " to " "
        )

        val stringBuilder = StringBuilder()

        for (character in charArray) {
            val myChar = character.toUpperCase()
            val morseChar = morseCodeMap.getValue(myChar.toString())
            stringBuilder.append(morseChar)

        }

        return stringBuilder.toString()
    }


}