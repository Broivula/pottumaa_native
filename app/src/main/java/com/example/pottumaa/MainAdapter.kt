package com.example.pottumaa

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_image_gallery.view.*
import kotlinx.android.synthetic.main.datebutton_row.view.*
import kotlinx.android.synthetic.main.gallery_image.view.*

class MainAdapter(val context: Context) : RecyclerView.Adapter<CustomViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.datebutton_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        //this looks mega friggin ugly, but i can't be bothered to fix it
        holder.view.textViewDate_main.text = DataManager.imageData!!.get(position).date.split("-").drop(1).joinToString { it }.replace(",", ".")
        holder.view.date_button.setOnClickListener { view ->
            val activityIntent = Intent(context, ImageGalleryActivity::class.java)
            activityIntent.putExtra("position", position)
            startActivity(context, activityIntent, null)
        }
    }



    // like ios, number of items returned
    override fun getItemCount(): Int {
     return DataManager.imageData!!.size
    }

}

class CustomViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

}

class GalleryAdapter (val data : ImageData) : RecyclerView.Adapter<CustomViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.gallery_image, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        // just for a single image
        val galleryImageView = holder.view.imageViewImage_gallery
        val imageUrl = "http://13.ip-51-75-16.eu:2222/get/potatoImages/"+data.date + "!" + data.images.get(position)
        println(imageUrl)
        Picasso.with(holder.view.context).load(imageUrl).placeholder(R.drawable.ic_image_white_24dp).into(galleryImageView)

        holder.view.imageViewImage_gallery.setOnClickListener{ view ->
            val activityIntent = Intent(holder.view.context, SingleImageActivity::class.java)
            activityIntent.putExtra("imageUrl", imageUrl)
            startActivity(holder.view.context, activityIntent, null)
        }
    }

    // like ios, number of items returned aka how many pictures we got that day
    override fun getItemCount(): Int {
      return data.images.count()
    }

}