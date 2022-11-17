package com.example.project_03

import android.app.DownloadManager
import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class second : AppCompatActivity() {
    var cur: String? = "https://meme-api.herokuapp.com/gimme"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        load()
        val sb = findViewById<Button>(R.id.showbtn)
        val nb = findViewById<Button>(R.id.nxtbtn)
        sb.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, "Man! check out this MEME $cur")
            val chooser = Intent.createChooser(intent, "Share This MEME ... ")
            startActivity(chooser)
        }
        nb.setOnClickListener {
            load()
        }
    }

    private fun load() {

// Instantiate the RequestQueue.

        val pb = findViewById<ProgressBar>(R.id.progress)
        pb.visibility = View.VISIBLE
        val queue = Volley.newRequestQueue(this)
        val url = "https://meme-api.herokuapp.com/gimme"

// Request a string response from the provided URL.
        val jesonObjectRequest = JsonObjectRequest(

            Request.Method.GET, url, null,
            { response ->
                val cur = response.getString("url")
                val iv = findViewById<ImageView>(R.id.imageView)
                Glide.with(this).load(cur).listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        pb.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: com.bumptech.glide.load.DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {

                        pb.visibility = View.GONE
                        return false
                    }
                }).into(iv)
            },
            {
                Toast.makeText(this, "Something Wrong", Toast.LENGTH_SHORT).show()
            })

// Add the request to the RequestQueue.
        queue.add(jesonObjectRequest)
    }

}
