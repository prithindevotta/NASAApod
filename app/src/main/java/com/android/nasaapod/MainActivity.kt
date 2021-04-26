package com.android.nasaapod

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.loader.app.LoaderManager
import androidx.loader.content.AsyncTaskLoader
import androidx.loader.content.Loader
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.*
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray

class MainActivity : AppCompatActivity(), ItemClicked  {

    private lateinit var mAdapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter = CustomAdapter(this)
        fetchData()
        recyclerView.adapter = mAdapter
    }

    private fun fetchData(){
        val url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&count=10"
        var APODList = ArrayList<APOD>()

        val jsonArrayRequest = JsonArrayRequest(Request.Method.GET, url, null,
            { response ->
                for (i in 0 until response.length()){
                    val jsonObject=response.getJSONObject(i)
                    val Apod = APOD(jsonObject.getString("date"), jsonObject.getString("explanation"), jsonObject.getString("title"), jsonObject.getString("url"))
                    APODList.add(Apod)
                }
                mAdapter.update(APODList)
            },
            {

            }
        )

// Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonArrayRequest)
    }

    override fun onItemClicked(item: APOD) {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("title", item.title)
        intent.putExtra("date", item.date)
        intent.putExtra("explanation", item.explanation)
        startActivity(intent)
    }
}