package com.example.pottumaa

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.MotionEventCompat
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_single_image.*

class SingleImageActivity : AppCompatActivity() {
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        this.supportActionBar?.hide()
        setContentView(R.layout.activity_single_image)
        val imageUrl = intent.getStringExtra("imageUrl")
        Picasso.with(this).load(URL +imageUrl).placeholder(R.drawable.ic_image_white_24dp).into(singleImage_View)

    }


}
