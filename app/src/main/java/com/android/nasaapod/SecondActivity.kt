package com.android.nasaapod

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import org.w3c.dom.Text
import java.io.InputStream
import java.lang.System.load
import java.util.concurrent.TimeUnit

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val bundle:Bundle? = intent.extras
        val title = findViewById<TextView>(R.id.title)
        title.text = bundle!!.getString("title")
        val dateView = findViewById<TextView>(R.id.date)
        dateView.text = bundle.getString("date")
        val explanation = findViewById<TextView>(R.id.explanation)
        explanation.text = bundle.getString("explanation")
    }
}
