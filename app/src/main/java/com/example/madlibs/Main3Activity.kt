package com.example.madlibs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main3.*
import java.util.*
import kotlin.collections.HashMap

class Main3Activity : AppCompatActivity() {
    private val words = mutableListOf<String>()
    private val keyToWords = HashMap<String, String> ()
    private var file = 0
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        if (intent != null){
            val storyName = intent.getStringExtra("storyName")
            file = when(storyName){
                "clothes" -> R.raw.clothes
                "dance" -> R.raw.dance
                "simple_story" -> R.raw.simple_story
                "dr_sykes_welcome" -> R.raw.dr_sykes_welcome
                "tarzan" -> R.raw.tarzan
                else -> R.raw.university
            }
            val input = Scanner(resources.openRawResource(file))
            while (input.hasNextLine()) {
                val line = input.nextLine()
                var word = ""
                var blank = false
                for (i in 0..line.length-1) {
                    if (line.get(i) == '>'){
                        words.add(word)
                        word = ""
                        blank = false
                    }
                    if (blank) {
                        word += line.get(i)
                    }
                    if (line.get(i) == '<'){
                        blank = true
                    }
                }
            }
        }
        userWords.hint = blankText(words.get(0))
        wordsTillDone.text = "${words.size} words remaining"
    }

    fun blankText(text: String) : String {
        var result = ""
        for (i in 0..text.length-1) {
            val c = text.get(i)
            if (c >= '0' && c <= '9') {} else if (c == '-') {
                result += " "
            } else {
                result += c
            }
        }
        return result
    }

    fun onClick(view: View) {
        count ++
        keyToWords[words.get(count-1)] = userWords.text.toString()
        if (count < words.size) {
            userWords.setText("")
            userWords.hint = blankText(words.get(count))
            wordsTillDone.text = "${words.size-count} words left"
        } else {
            val myIntent = Intent (this, Main4Activity::class.java)
            myIntent.putExtra("values", keyToWords)
            myIntent.putExtra("file", file)
            startActivity(myIntent)
        }
    }


}
