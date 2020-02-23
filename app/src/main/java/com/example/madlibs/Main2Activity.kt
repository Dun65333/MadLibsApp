package com.example.madlibs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {
    private val storyList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        storyList.add("simple_story")
        storyList.add("clothes")
        storyList.add("dr_sykes_welcome")
        storyList.add("dance")
        storyList.add("tarzan")
        storyList.add("university")

        var myAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, storyList )

        storyListView.adapter = myAdapter

        storyListView.setOnItemClickListener {
            parent, view, position, id ->
            val storyName = storyList[position]
            val myIntent = Intent(this, Main3Activity::class.java)
            myIntent.putExtra("storyName", storyName)
            startActivity(myIntent)
        }
    }
}
