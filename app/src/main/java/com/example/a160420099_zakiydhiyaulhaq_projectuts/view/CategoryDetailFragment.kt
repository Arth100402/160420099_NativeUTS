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
import com.example.a160420099_zakiydhiyaulhaq_projectuts.viewmodel.CategoryDetailViewModel

class CategoryDetailFragment : Fragment() {
    private lateinit var categoryDetailViewModel: CategoryDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var imageViewCatDet = view.findViewById<ImageView>(R.id.imageViewCatDet)
        var textNamaCategoryDetail = view.findViewById<TextView>(R.id.textNamaCategoryDetail)
        var textDescCategoryDetail = view.findViewById<TextView>(R.id.textDescCategoryDetail)
        var progressBarCatDet = view.findViewById<ProgressBar>(R.id.progressBarCatDet)


        categoryDetailViewModel = ViewModelProvider(this).get(CategoryDetailViewModel::class.java)
        categoryDetailViewModel.fetch(CategoryDetailFragmentArgs.fromBundle(requireArguments()).categoryID)

        categoryDetailViewModel.categoryLD.observe(viewLifecycleOwner){ categoryDetail ->
            imageViewCatDet.loadImage(categoryDetail.photoUrl, progressBarCatDet)
            textNamaCategoryDetail.text = categoryDetail.nama.toString()
            textDescCategoryDetail.text = categoryDetail.description.toString()
        }
    }
}