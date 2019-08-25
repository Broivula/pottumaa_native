package com.example.pottumaa

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Button
import kotlinx.android.synthetic.main.activity_image_gallery.*

class ImageGalleryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_gallery)
        var position = intent.getIntExtra("position", 0)

        this.supportActionBar?.title = DataManager.imageData?.get(position)?.date?.split("-")?.drop(1)?.joinToString { it }?.replace(",", ".")?.trim()
            // DataManager.imageData!!.get(position).date.split("-").drop(1).joinToString { it }.replace(",", ".")


        recyclerView_gallery.layoutManager = LinearLayoutManager(this)
        recyclerView_gallery.adapter = GalleryAdapter(DataManager.imageData!!.get(position))
    }

}
