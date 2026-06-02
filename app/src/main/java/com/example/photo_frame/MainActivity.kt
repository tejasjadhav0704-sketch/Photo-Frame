package com.example.photo_frame

import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.photo_frame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        onBackPressedDispatcher.addCallback(this) {
            finish()
        }

        val images = arrayOf(
            R.drawable.welcome_bird,
            R.drawable.speedarray,
            R.drawable.pigarray,
            R.drawable.babybirdarray,
            R.drawable.bombarray
        )

        var currentImgIndex = 0

        Glide.with(this)
            .asGif()
            .load(images[currentImgIndex])
            .into(binding.imageView7)

        binding.nextbutton.setOnClickListener {
            currentImgIndex = (currentImgIndex + 1) % images.size
            Glide.with(this)
                .asGif()
                .load(images[currentImgIndex])
                .into(binding.imageView7)
        }

        binding.prevButton.setOnClickListener {
            currentImgIndex = (currentImgIndex - 1 + images.size) % images.size
            Glide.with(this)
                .asGif()
                .load(images[currentImgIndex])
                .into(binding.imageView7)
        }
    }
}
