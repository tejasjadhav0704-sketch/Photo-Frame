package com.example.photo_frame

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.materialswitch.MaterialSwitch

class Welcome_page : AppCompatActivity() {

    private lateinit var switch1: MaterialSwitch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome_page)

        switch1 = findViewById(R.id.switch1)
        val img3 = findViewById<ImageView>(R.id.imageView3)

        switch1.setOnClickListener {
            startActivity(Intent(this, Login_Activity::class.java))
        }

        Glide.with(this)
            .asGif()
            .load(R.drawable.welcome_bird)
            .into(img3)
    }

    override fun onResume() {
        super.onResume()
        if (::switch1.isInitialized) {
            switch1.isChecked = false
        }
    }
}
