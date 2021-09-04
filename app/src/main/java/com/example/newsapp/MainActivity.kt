package com.example.newsapp

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), onClickitem {
    private val Tag = "MainActivity"
    private lateinit var madapter: Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        my_recyclerview.layoutManager = LinearLayoutManager(this)
        Log.d(Tag, "mainActivity call")
        fetchdata()
        madapter = Adapter(this)
        my_recyclerview.adapter = madapter


    }

    fun fetchdata() {

//
//        val api="e93846fb9e4579eba1e14825650f208c"
        val url =
           "https://gnews.io/api/v4/search?q=example&token=3b775e0013422d8549b9607b659758f6&lang=en"
//        val queue = Volley.newRequestQueue(this)
        Log.e("MainActivity", "url")
        val jsonRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            {


                Log.e("MainActivity", "response.listener")
                val jsonarray = it.getJSONArray("articles")
                var newsArray = ArrayList<news>()
                for (i in 0 until jsonarray.length()) {
                    val nwsobject = jsonarray.getJSONObject(i)

                    val news1 = news(
                        nwsobject.getString("title"),
                        nwsobject.getString("description"), nwsobject.getString("url"),
                        nwsobject.getString("image")
                    )
                    newsArray.add(news1)
                }

                Log.e("MainActivity", "updatedata")
                madapter.updatedata(newsArray)
            },
            {
                Log.e("MainActivity",it.toString())
                Log.e("MainActivity", "error in api call")
                Toast.makeText(
                    this,
                    "problem in api calling",
                    Toast.LENGTH_SHORT
                ).show()
            }

        )



//        queue.add(jsonRequest)
        mySingleton.getInstance(this).addToRequestQueue(jsonRequest)

    }

    override fun OnClick(news1: news) {

        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(news1.url))
    }
}