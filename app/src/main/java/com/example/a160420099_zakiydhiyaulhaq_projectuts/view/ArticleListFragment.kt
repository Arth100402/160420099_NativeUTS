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
import com.example.a160420099_zakiydhiyaulhaq_projectuts.R
import com.example.a160420099_zakiydhiyaulhaq_projectuts.viewmodel.ArticleListViewModel

class ArticleListFragment : Fragment() {
    private lateinit var viewModel: ArticleListViewModel
    private val articleListAdapter = ArticleListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ArticleListViewModel::class.java)
        viewModel.refresh()
        val recView = view?.findViewById<RecyclerView>(R.id.recViewArt)
        recView?.layoutManager = LinearLayoutManager(context)
        recView?.adapter = articleListAdapter
        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.articlesLD.observe(viewLifecycleOwner, Observer {
            articleListAdapter.updateArticleList(it)
        })
        viewModel.articleLoadErrorLD.observe(viewLifecycleOwner, Observer {
            val txtErrorArt = view?.findViewById<TextView>(R.id.txtErrorArt)
            if(it == true) {
                txtErrorArt?.visibility = View.VISIBLE
            } else {
                txtErrorArt?.visibility = View.GONE
            }
        })
        viewModel.articleloadingLD.observe(viewLifecycleOwner, Observer {
            val recView = view?.findViewById<RecyclerView>(R.id.recViewArt)
            val progressLoad = view?.findViewById<ProgressBar>(R.id.progressLoadArt)
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