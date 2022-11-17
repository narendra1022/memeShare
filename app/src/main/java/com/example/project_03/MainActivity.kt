package com.example.project_03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val name=findViewById<Button>(R.id.btn)
        name.setOnClickListener{
            call()
        }
    }
    private fun call(){
        val intent= Intent(this@MainActivity,second::class.java).also {
            startActivity(it)
        }
    }
}