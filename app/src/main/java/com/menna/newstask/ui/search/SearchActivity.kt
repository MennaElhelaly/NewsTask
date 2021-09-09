package com.menna.newstask.ui.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationapp.ui.cart.adapter.HomeAdapter
import com.menna.newstask.R
import com.menna.newstask.data_layer.entity.Article
import com.menna.newstask.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity(),  HomeAdapter.OnHomeItemListener{
    lateinit var binding: ActivitySearchBinding
    private lateinit var searchViewMode: SearchViewModel
    private var searchAdapter = HomeAdapter(arrayListOf(),this)
    private lateinit var filteredList: ArrayList<Article>
    private lateinit var vendorFilteredList: ArrayList<Article>

    private lateinit var allList: ArrayList<Article>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        //hide actionBar
        val actionBar = supportActionBar
        actionBar?.hide()
        binding = ActivitySearchBinding.inflate(layoutInflater)
        searchViewMode = ViewModelProvider(this).get(SearchViewModel::class.java)
        searchViewMode.getAPINews()


        allList = ArrayList()
        vendorFilteredList = ArrayList()
        filteredList = ArrayList()
        initUi()
        searchAdapter.updateData(filteredList)

        searchViewMode.newsArticlesLiveData.observe(this, Observer {
            it?.let {
                allList = it as ArrayList<Article>
                filteredList=allList
                vendorFilteredList = allList
                searchAdapter.updateData(filteredList)
            }

        })
        binding.searchtv.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                filter(s.toString())
            }
        })

    }
    private fun filter(text: String) {
        val filteredList1: ArrayList<Article> = ArrayList()
        for (item in vendorFilteredList) {
            if (item.title.toLowerCase()
                    .contains(text.toLowerCase()) || item.author.toLowerCase()
                    .contains(text.toLowerCase())
            ) {
                filteredList1.add(item)
            }
        }

        filteredList = filteredList1
        searchAdapter.updateData(filteredList)
    }
    private fun initUi() {
        binding.searchRecycle.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = searchAdapter

        }

    }

    override fun onImageClick(item: Article) {
        TODO("Not yet implemented")
    }
}