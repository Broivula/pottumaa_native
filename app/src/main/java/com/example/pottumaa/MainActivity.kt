package com.example.pottumaa

import android.content.Intent
import android.graphics.Color
import android.media.Image
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v7.widget.LinearLayoutManager
import android.widget.Button
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.datebutton_row.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DataManager.fetchJSONData(this, this)

        //recyclerView_main.setBackgroundColor(Color.LTGRAY)
        //DataManager.run("http://13.ip-51-75-16.eu:2222/get/potatoImages")

        recyclerView_main.layoutManager = LinearLayoutManager(this)

        val dateButton = findViewById<Button>(R.id.date_button)

        //   recyclerView_main.adapter = MainAdapter()
    }



    fun attachMainAdapter(adapter : MainAdapter){
        runOnUiThread {
            recyclerView_main.adapter = adapter
        }

    }


}
