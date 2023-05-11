package com.example.a160420099_zakiydhiyaulhaq_projectuts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.a160420099_zakiydhiyaulhaq_projectuts.R
import com.example.a160420099_zakiydhiyaulhaq_projectuts.viewmodel.BookListViewModel

class BookListFragment : Fragment() {
    private lateinit var viewModel: BookListViewModel
    private val bookListAdapter = BookListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(BookListViewModel::class.java)
        viewModel.refresh()
        val recView = view?.findViewById<RecyclerView>(R.id.recViewBook)
        recView?.layoutManager = LinearLayoutManager(context)
        recView?.adapter = bookListAdapter

        val refreshLayoutBook = view?.findViewById<SwipeRefreshLayout>(R.id.refreshLayoutBook)
        val txtError = view?.findViewById<TextView>(R.id.txtError)
        val progressLoad = view?.findViewById<ProgressBar>(R.id.progressLoad)
        refreshLayoutBook?.setOnRefreshListener {
            recView?.visibility = View.GONE
            txtError?.visibility = View.GONE
            progressLoad?.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayoutBook.isRefreshing = false
        }
        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.booksLD.observe(viewLifecycleOwner, Observer {
            bookListAdapter.updateBookList(it)
        })
        viewModel.bookLoadErrorLD.observe(viewLifecycleOwner, Observer {
            val txtError = view?.findViewById<TextView>(R.id.txtError)
            if(it == true) {
                txtError?.visibility = View.VISIBLE
            } else {
                txtError?.visibility = View.GONE
            }
        })
        viewModel.bookloadingLD.observe(viewLifecycleOwner, Observer {
            val recView = view?.findViewById<RecyclerView>(R.id.recViewBook)
            val progressLoad = view?.findViewById<ProgressBar>(R.id.progressLoad)
            if(it == true) {
                recView?.visibility = View.GONE
                progressLoad?.visibility = View.VISIBLE
            } else {
                recView?.visibility = View.VISIBLE
                progressLoad?.visibility = View.GONE
            }
        })
    }
}