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
import com.example.a160420099_zakiydhiyaulhaq_projectuts.viewmodel.BookDetailViewModel


class BookDetailFragment : Fragment() {
    private lateinit var bookDetailViewModel: BookDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var imageViewDetBook = view.findViewById<ImageView>(R.id.imageViewDetBook)
        var textJudulBookDetail = view.findViewById<TextView>(R.id.textJudulBookDetail)
        var textPenulisBookDetail = view.findViewById<TextView>(R.id.textPenulisBookDetail)
        var textPublisherBookDetail = view.findViewById<TextView>(R.id.textPublisherBookDetail)
        var textDescBookDetail = view.findViewById<TextView>(R.id.textDescBookDetail)
        var textCategoryBookDetail = view.findViewById<TextView>(R.id.textCategoryBookDetail)
        var textNoBookDetail = view.findViewById<TextView>(R.id.textNoBookDetail)
        var textPagesDetailBook = view.findViewById<TextView>(R.id.textPagesDetailBook)
        var textLanguageBookDetail = view.findViewById<TextView>(R.id.textLanguageBookDetail)
        var progressBarBookDet = view.findViewById<ProgressBar>(R.id.progressBarBookDet)
        val btnBorrow = view.findViewById<Button>(R.id.btnBorrow)


        bookDetailViewModel = ViewModelProvider(this).get(BookDetailViewModel::class.java)
        bookDetailViewModel.fetch(BookDetailFragmentArgs.fromBundle(requireArguments()).bookID)

        bookDetailViewModel.bookLD.observe(viewLifecycleOwner){ bookDetail ->
            imageViewDetBook.loadImage(bookDetail.photoUrl, progressBarBookDet)
            textJudulBookDetail.text = bookDetail.judul.toString()
            textPenulisBookDetail.text = bookDetail.penulis.toString()
            textPublisherBookDetail.text = bookDetail.publisher.toString()
            textDescBookDetail.text = bookDetail.description.toString()
            textCategoryBookDetail.text = bookDetail.category.toString()
            textNoBookDetail.text = bookDetail.bookno.toString()
            textPagesDetailBook.text = bookDetail.pages.toString()
            textLanguageBookDetail.text = bookDetail.language.toString()
        }
    }

}