package com.example.latestnews.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.latestnews.R
import com.example.latestnews.model.Article
import com.example.latestnews.utils.TAG
import kotlinx.android.synthetic.main.item_news.view.*

class NewsAdapter(var article: ArrayList<Article>,var context: Context) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
    )

    override fun getItemCount(): Int = article.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(article = article[position], pos = position,context = context)
    }

    fun updateUsers(newUsers: List<Article>) {
        article.clear()
        Log.d(TAG, newUsers.size.toString())
        article.addAll(newUsers)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title = view.title
        private val desc = view.desc
        private val author = view.author
        private val publishedId = view.publishedId
        private val source = view.source
        private val time = view.time
        private val img = view.img


        fun onBind(article: Article, pos: Int,context: Context) {
            var publishedId = "Null"
            var name = "No name"
            if (article.sourse != null) {
                publishedId = article.sourse!![pos].id.toString()
                name = article.sourse!![pos].name.toString()
            }
            title.text = article.title
            desc.text = article.description
            author.text = article.author
            this.publishedId.text = publishedId
            source.text = name
            time.text = article.publishedAt

            Glide.with(context)
                .load(article.urlToImage)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(img)
        }
    }
}