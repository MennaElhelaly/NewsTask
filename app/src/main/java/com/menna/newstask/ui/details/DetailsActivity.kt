package com.menna.newstask.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.menna.newstask.R
import com.menna.newstask.databinding.ActivityDetailsBinding


class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent =intent
        if (intent != null) {
            Glide.with(binding.imageView.context).load(intent.getStringExtra("image")).placeholder(R.drawable.no_image).into(binding.imageView)
            binding.title.text = intent.getStringExtra("title")
            binding.description.text = intent.getStringExtra("description")
        }

    }
}