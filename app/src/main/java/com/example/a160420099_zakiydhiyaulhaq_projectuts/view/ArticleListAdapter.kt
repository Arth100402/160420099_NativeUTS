package com.example.a160420099_zakiydhiyaulhaq_projectuts.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.a160420099_zakiydhiyaulhaq_projectuts.R
import com.example.a160420099_zakiydhiyaulhaq_projectuts.model.Article
import com.example.a160420099_zakiydhiyaulhaq_projectuts.util.loadImage

class ArticleListAdapter(val articleList:ArrayList<Article>):RecyclerView.Adapter<ArticleListAdapter.ArticleViewHolder>() {
    class ArticleViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.article_list_item, parent, false)
        return ArticleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val textJudulArticle = holder.view.findViewById<TextView>(R.id.textJudulArticle)
        val textPenulisArticle = holder.view.findViewById<TextView>(R.id.textPenulisArticle)
        val btnDetailArticle = holder.view.findViewById<Button>(R.id.btnDetailArticle)
        textJudulArticle.text = articleList[position].judul
        textPenulisArticle.text = articleList[position].penulis
        btnDetailArticle.setOnClickListener{
            val action = ArticleListFragmentDirections.actionArticleDetail(articleList[position].id.toString())
            Navigation.findNavController(it).navigate(action)
        }
        var imageView = holder.view.findViewById<ImageView>(R.id.imageViewArt)
        var progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBarArt)
        imageView.loadImage(articleList[position].photoUrl, progressBar)

    }

    fun updateArticleList(newArticleList: ArrayList<Article>) {
        articleList.clear()
        articleList.addAll(newArticleList)
        notifyDataSetChanged()
    }
}