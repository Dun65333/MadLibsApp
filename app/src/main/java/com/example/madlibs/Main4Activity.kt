package com.example.madlibs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main4.*
import java.util.*
import kotlin.collections.HashMap

class Main4Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        if (intent != null) {
            val values = intent.getSerializableExtra("values") as HashMap<String, String>
            val file = intent.getIntExtra("file", 0)
            var result = ""
            val input = Scanner(resources.openRawResource(file))
            while (input.hasNextLine()) {
                val line = input.nextLine()
                var word = ""
                var blank = false
                for (i in 0..line.length-1) {
                    if (line.get(i) == '>'){
                        result += values[word]
                        word = ""
                        blank = false
                    }
                    if (blank) {
                        word += line.get(i)
                    } else if (line.get(i) != '>' && line.get(i) != '<') {
                        result += line.get(i)
                    }
                    if (line.get(i) == '<'){
                        blank = true
                    }
                }

                result += ""
            }
            storyText.text = result
        }
    }
}
