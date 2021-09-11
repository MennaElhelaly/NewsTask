package com.menna.newstask.ui.home

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graduationapp.ui.cart.adapter.HomeAdapter
import com.example.graduationapp.utils.Validation
import com.menna.newstask.R
import com.menna.newstask.data_layer.entity.Article
import com.menna.newstask.databinding.ActivityHomeBinding
import com.menna.newstask.ui.details.DetailsActivity

class HomeActivity : AppCompatActivity(), HomeAdapter.OnHomeItemListener {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: ActivityHomeBinding
    private lateinit var filteredList: ArrayList<Article>
    private var homeAdapter = HomeAdapter(arrayListOf(),this)

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        binding = ActivityHomeBinding.inflate(layoutInflater)
        //hide actionBar
        val actionBar = supportActionBar
        actionBar?.hide()
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        setContentView(binding.root)
        //check network
        if (Validation.isOnline(this)) {
            homeViewModel.getAPINews()
        }
        else
        {
            Toast.makeText(this,getString(R.string.no_internet), Toast.LENGTH_LONG).show()
        }

        filteredList = ArrayList()
        homeViewModel.newsArticlesLiveData.observe(this, Observer {
            homeAdapter.updateData(it)
            filteredList=ArrayList(it)
            homeAdapter.notifyDataSetChanged()
        })
        initUI()
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                homeAdapter.updateData( filteredList.filter { it.title.contains(newText) })
                homeAdapter.notifyDataSetChanged()
                return true
            }
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
        item.description?.let {
            intent.putExtra("description",it)
        } ?:  intent.putExtra("description",getString(R.string.no_description))

        startActivity(intent)
    }
}