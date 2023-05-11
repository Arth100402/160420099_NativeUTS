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
import com.example.a160420099_zakiydhiyaulhaq_projectuts.model.Book
import com.example.a160420099_zakiydhiyaulhaq_projectuts.util.loadImage

class BookListAdapter(val bookList:ArrayList<Book>): RecyclerView.Adapter<BookListAdapter.BookViewHolder>() {
    class BookViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.book_list_item, parent, false)
        return BookViewHolder(view)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val textJudulBook = holder.view.findViewById<TextView>(R.id.textJudulBook)
        val textPenulisBook = holder.view.findViewById<TextView>(R.id.textPenulisBook)
        val btnDetailBook = holder.view.findViewById<Button>(R.id.btnDetailBook)
        val idBook = bookList[position].id.toString()
        textJudulBook.text = bookList[position].judul
        textPenulisBook.text = bookList[position].penulis
        btnDetailBook.setOnClickListener{
            val action = BookListFragmentDirections.actionBookDetail(idBook)
            Navigation.findNavController(it).navigate(action)
        }
        var imageView = holder.view.findViewById<ImageView>(R.id.imageViewBook)
        var progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBarBook)
        imageView.loadImage(bookList[position].photoUrl, progressBar)
    }

    fun updateBookList(newBookList: ArrayList<Book>) {
        bookList.clear()
        bookList.addAll(newBookList)
        notifyDataSetChanged()
    }
}