package com.example.a160420099_zakiydhiyaulhaq_projectuts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.a160420099_zakiydhiyaulhaq_projectuts.R
import com.example.a160420099_zakiydhiyaulhaq_projectuts.util.loadImage
import com.example.a160420099_zakiydhiyaulhaq_projectuts.viewmodel.ArticleDetailViewModel
import com.example.a160420099_zakiydhiyaulhaq_projectuts.viewmodel.BookDetailViewModel

class ArticleDetailFragment : Fragment() {
    private lateinit var articleDetailViewModel: ArticleDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var imageViewArtDet = view.findViewById<ImageView>(R.id.imageViewArtDet)
        var textJudulArticleDetail = view.findViewById<TextView>(R.id.textJudulArticleDetail)
        var textPenulisArticleDetail = view.findViewById<TextView>(R.id.textPenulisArticleDetail)
        var textDescArticleDetail = view.findViewById<TextView>(R.id.textDescArticleDetail)
        var progressBarArtDet = view.findViewById<ProgressBar>(R.id.progressBarArtDet)


        articleDetailViewModel = ViewModelProvider(this).get(ArticleDetailViewModel::class.java)
        articleDetailViewModel.fetch(ArticleDetailFragmentArgs.fromBundle(requireArguments()).articleID)

        articleDetailViewModel.articleLD.observe(viewLifecycleOwner){ articleDetail ->
            imageViewArtDet.loadImage(articleDetail.photoUrl, progressBarArtDet)
            textJudulArticleDetail.text = articleDetail.judul.toString()
            textPenulisArticleDetail.text = articleDetail.penulis.toString()
            textDescArticleDetail.text = articleDetail.description.toString()
        }
    }
}