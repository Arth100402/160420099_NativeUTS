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
import com.example.a160420099_zakiydhiyaulhaq_projectuts.viewmodel.MyFavoriteDetailViewModel

class MyFavoriteDetailFragment : Fragment() {
    private lateinit var myFavoriteDetailViewModel: MyFavoriteDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_favorite_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var imageViewDetMy = view.findViewById<ImageView>(R.id.imageViewDetMy)
        var textJudulMyDetail = view.findViewById<TextView>(R.id.textJudulMyDetail)
        var textPenulisMyDetail = view.findViewById<TextView>(R.id.textPenulisMyDetail)
        var textPublisherMyDetail = view.findViewById<TextView>(R.id.textPublisherMyDetail)
        var textDescMyDetail = view.findViewById<TextView>(R.id.textDescMyDetail)
        var textCategoryMyDetail = view.findViewById<TextView>(R.id.textCategoryMyDetail)
        var textNoMyDetail = view.findViewById<TextView>(R.id.textNoMyDetail)
        var textPagesDetailMy = view.findViewById<TextView>(R.id.textPagesDetailMy)
        var textLanguageMyDetail = view.findViewById<TextView>(R.id.textLanguageMyDetail)
        var progressBarMyDet = view.findViewById<ProgressBar>(R.id.progressBarMyDet)
        val btnBorrowMy = view.findViewById<Button>(R.id.btnBorrowMy)


        myFavoriteDetailViewModel = ViewModelProvider(this).get(MyFavoriteDetailViewModel::class.java)
        myFavoriteDetailViewModel.fetch(MyFavoriteDetailFragmentArgs.fromBundle(requireArguments()).myfavoriteID)

        myFavoriteDetailViewModel.myfavoriteLD.observe(viewLifecycleOwner){ myfavoriteDetail ->
            imageViewDetMy.loadImage(myfavoriteDetail.photoUrl, progressBarMyDet)
            textJudulMyDetail.text = myfavoriteDetail.judul.toString()
            textPenulisMyDetail.text = myfavoriteDetail.penulis.toString()
            textPublisherMyDetail.text = myfavoriteDetail.publisher.toString()
            textDescMyDetail.text = myfavoriteDetail.description.toString()
            textCategoryMyDetail.text = myfavoriteDetail.category.toString()
            textNoMyDetail.text = myfavoriteDetail.bookno.toString()
            textPagesDetailMy.text = myfavoriteDetail.pages.toString()
            textLanguageMyDetail.text = myfavoriteDetail.language.toString()
        }
    }
}