package com.bharathkalyans.morsecodegenerator

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bharathkalyans.morsecodegenerator.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

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
            " " to " ",
            "!" to "!",
            "~" to "~",
            "`" to "`",
            "@" to "@",
            "$" to "$",
            "#" to "#",
            "%" to "%",
            "^" to "^",
            "&" to "&",
            "-" to "-",
            "+" to "+",
            ";" to ";",
            ":" to ":",
            "/" to "/",
            "{" to "{",
            "[" to "[",
            "]" to "]",
            "}" to "}",
            "|" to "|",
        )

        val stringBuilder = StringBuilder()

        for (character in charArray) {
            val myChar = character.toUpperCase()
            val morseChar = morseCodeMap.getValue(myChar.toString()).plus(" ")
            stringBuilder.append(morseChar)

        }

        return stringBuilder.toString()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.custom_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.copyMorseCode -> {
                val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val text = binding.tvMorseCode.text.toString()
                val clip: ClipData =
                    ClipData.newPlainText("Morse Code", text)

                clipboard.setPrimaryClip(clip)
                Snackbar.make(this, binding.etPlainText, "Morse Code Copied!", Snackbar.LENGTH_LONG)
                    .setAction(R.string.undo) {
                        clipboard.clearPrimaryClip()
                    }
                    .show()
                true
            }
            R.id.clearAllFields -> {
                clearAllFields()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun clearAllFields() {
        binding.tvMorseCode.text = ""
        binding.etPlainText.text = null
        Toast.makeText(this, "Cleared All Fields!", Toast.LENGTH_SHORT).show()
    }


}