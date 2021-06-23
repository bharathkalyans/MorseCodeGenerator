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



        viewModel.name.observe(this, {
            tvMorseCode.text = it.toString()
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

}