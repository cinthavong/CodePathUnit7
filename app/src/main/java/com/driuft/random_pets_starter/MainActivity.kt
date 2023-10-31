package com.driuft.random_pets_starter

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {
    private lateinit var catsList: MutableList<String>
    //private lateinit var catsBreed: MutableList<String>
    private lateinit var recyclerViewCats: RecyclerView
    //lateinit means defining a variable later, commonly used to define a class variable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerViewCats = findViewById(R.id.catRecyclerView)
        catsList = mutableListOf() // list of photo urls
        //catsBreed = mutableListOf()

        getCatImageURL()
    }

    private fun getCatImageURL() {
        val client = AsyncHttpClient()

        client["https://api.thecatapi.com/v1/images/search?limit=20", object : JsonHttpResponseHandler() {
            //https://api.thecatapi.com/v1/images/search?limit=20
            override fun onSuccess(
                statusCode: Int,
                headers: Headers,
                json: JsonHttpResponseHandler.JSON
            ) {
                Log.d("Cat Success", "response successful$json")
                //Log.d("Dog Success",json.jsonArray.toString())

                val catImageArray = json.jsonArray
                for (i in 0 until catImageArray.length()) {
                    catsList.add(catImageArray.getJSONObject(i).getString("url"))

                    //catsBreed.add(catImageArray.getJSONObject(i).getString("breed"))



//                val dogImageArray = json.jsonObject.getJSONArray("message")
//                for (i in 0 until dogImageArray.length()) {
//                    dogsList.add(dogImageArray.getString(i))
                }

                val catAdapter = CatAdapter(catsList)
                recyclerViewCats.adapter = catAdapter
                recyclerViewCats.layoutManager = LinearLayoutManager(this@MainActivity)
                recyclerViewCats.addItemDecoration(
                    DividerItemDecoration(
                        this@MainActivity,
                        LinearLayoutManager.VERTICAL
                    )
                )
            }



            override fun onFailure(
                statusCode: Int,
                headers: Headers,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Cat Error", errorResponse)
            }
        }]


    }
}