package com.menna.newstask.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graduationapp.ui.cart.adapter.HomeAdapter
import com.menna.newstask.data_layer.entity.Article
import com.menna.newstask.databinding.ActivityHomeBinding
import com.menna.newstask.ui.details.DetailsActivity
import com.menna.newstask.ui.search.SearchActivity

class HomeActivity : AppCompatActivity(), HomeAdapter.OnHomeItemListener {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: ActivityHomeBinding
    private var homeAdapter = HomeAdapter(arrayListOf(),this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        binding = ActivityHomeBinding.inflate(layoutInflater)
        //hide actionBar
        val actionBar = supportActionBar
        actionBar?.hide()
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        setContentView(binding.root)
        homeViewModel.getAPINews()

        homeViewModel.newsArticlesLiveData.observe(this, Observer {
            homeAdapter.updateData(it)
        })
        initUI()

        binding.search.setOnClickListener(View.OnClickListener {
            val intent= Intent(this, SearchActivity::class.java)
            startActivity(intent)

        })

    }
    private fun initUI() {
        binding.recyclerShopBag.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = homeAdapter
        }

    }

    override fun onItemClick(item: Article) {
        val intent= Intent(this, DetailsActivity::class.java)
        intent.putExtra("image",item.urlToImage )
        intent.putExtra("title",item.title)
        intent.putExtra("description",item.description)
        startActivity(intent)
    }
}