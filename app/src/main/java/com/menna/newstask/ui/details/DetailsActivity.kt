package com.menna.newstask.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.menna.newstask.R
import com.menna.newstask.databinding.ActivityDetailsBinding
import com.menna.newstask.databinding.ActivityHomeBinding
import com.menna.newstask.ui.home.HomeViewModel

class DetailsActivity : AppCompatActivity() {
    private lateinit var detailsViewModel: DetailsViewModel
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)

        setContentView(binding.root)


        val intent =intent
        if (intent != null) {
            Log.i("Menna", "onCreate: inside intent")
            Glide.with(binding.imageView.context).load(intent.getStringExtra("image")).placeholder(R.drawable.no_image).into(binding.imageView)
            binding.title.text = intent.getStringExtra("title")
            binding.description.text = intent.getStringExtra("description")
        }

    }
}