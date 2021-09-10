package com.example.graduationapp.ui.cart.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.menna.newstask.R
import com.menna.newstask.data_layer.entity.Article

class HomeAdapter(var news: ArrayList<Article>, var listener: OnHomeItemListener) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    fun updateData(newCategory: List<Article>) {
        news.clear()
        news.addAll(newCategory)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = HomeViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.news_card, parent, false)
    )
    override fun getItemCount() = news.size
    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(news[position])

    }
    inner class HomeViewHolder(val view: View) : RecyclerView.ViewHolder(view) , View.OnClickListener {
        private val name = view.findViewById<TextView>(R.id.source_name)
        private val title = view.findViewById<TextView>(R.id.title)
        private val imageView = view.findViewById<ImageView>(R.id.image)

        fun bind(article: Article) {
            article.urlToImage.let { Glide.with(imageView.context).load(article.urlToImage).placeholder(R.drawable.no_image).into(imageView) }
            name.text =article.source.name
            title.text=article.title

        }
        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(p0: View?) {
            when(p0){
                itemView ->{
                    listener.onItemClick(news[adapterPosition])
                }
            }
        }
    }
    interface OnHomeItemListener
    {
        fun onItemClick(item: Article)
    }
}