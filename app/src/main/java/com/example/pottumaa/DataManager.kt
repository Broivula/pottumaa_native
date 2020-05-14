package com.example.pottumaa

import android.content.Context
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

object DataManager {
    var imageData : List<ImageData>? = null
    private val client = OkHttpClient()

    // This function gets the data from the server, sorts it, then passes the turn for the main thread to update the UI


    fun fetchJSONData(activity : MainActivity, context : Context) {

        val request = Request.Builder()
            .url(URL)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println(LOG_TEXT + " something went wrong with the request")
                println(e.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                println(LOG_TEXT + " request")
                val gson = GsonBuilder().setPrettyPrinting().create()
                val body = response.body()?.string()
                println(body)
                imageData = sortData(gson.fromJson(body, Array<ImageData>::class.java).toMutableList())

                activity.attachMainAdapter(MainAdapter(context))

            }
        })
    }


    fun sortData(list: MutableList<ImageData>): List<ImageData>? {

        var i: Int = 1;

        // so let's do a sorting algorithm.
        // basically what we want to do, is to compare the month of all the dates, and sort the parents accordingly
        // for now let's aim the big Oh for fairly bad n^2, in the future maybe strive to get it to n log n (unlikely)

        // so for starters let's compare our first two elements.

        for(elements in list.indices)
        {
            i = 1;
            while (i < list.count()) {
                // so now let's compare the first and the second element.. if returns true, then the previous element is smaller than current one ..
                // and we don't have to do anything, otherwise switch places
                if (compareImageData(list[i - 1], list[i])) {
                    // previous one is bigger, we need to switch positions
                    val current = list[i]
                    list.removeAt(i)
                    list.add(i-1, current)

                } else {
                    i++
                }
            }
        }
        println("sorting done")
        return list
    }


    fun compareImageData(first: ImageData, second: ImageData) : Boolean{
        return first > second
    }

}
data class ImageData (val date: String, val images: ArrayList<String>) : Comparable<ImageData>{

    override fun compareTo(other: ImageData): Int {
        return dateToInt(date) - dateToInt(other.date)
    }

    private fun dateToInt (date: String) : Int {return date.split("-")[2].toInt()}}

