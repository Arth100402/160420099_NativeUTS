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
import com.example.a160420099_zakiydhiyaulhaq_projectuts.viewmodel.CategoryListViewModel

class CategoryListFragment : Fragment() {
    private lateinit var viewModel: CategoryListViewModel
    private val categoryListAdapter = CategoryListAdapter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CategoryListViewModel::class.java)
        viewModel.refresh()
        val recView = view?.findViewById<RecyclerView>(R.id.recViewCat)
        recView?.layoutManager = LinearLayoutManager(context)
        recView?.adapter = categoryListAdapter
        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.categorysLD.observe(viewLifecycleOwner, Observer {
            categoryListAdapter.updateCategoryList(it)
        })
        viewModel.categoryLoadErrorLD.observe(viewLifecycleOwner, Observer {
            val txtError = view?.findViewById<TextView>(R.id.txtErrorCat)
            if(it == true) {
                txtError?.visibility = View.VISIBLE
            } else {
                txtError?.visibility = View.GONE
            }
        })
        viewModel.categoryloadingLD.observe(viewLifecycleOwner, Observer {
            val recView = view?.findViewById<RecyclerView>(R.id.recViewCat)
            val progressLoad = view?.findViewById<ProgressBar>(R.id.progressLoadCat)
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