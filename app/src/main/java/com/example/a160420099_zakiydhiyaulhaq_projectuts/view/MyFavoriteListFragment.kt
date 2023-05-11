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
import com.example.a160420099_zakiydhiyaulhaq_projectuts.model.MyFavorite
import com.example.a160420099_zakiydhiyaulhaq_projectuts.viewmodel.FacilityListViewModel
import com.example.a160420099_zakiydhiyaulhaq_projectuts.viewmodel.MyFavoriteListViewModel

class MyFavoriteListFragment : Fragment() {
    private lateinit var viewModel: MyFavoriteListViewModel
    private val myfavoriteListAdapter = MyFavoriteListAdapter(arrayListOf())
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
        return inflater.inflate(R.layout.fragment_my_favorite_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MyFavoriteListViewModel::class.java)
        viewModel.refresh()
        val recView = view?.findViewById<RecyclerView>(R.id.recViewMy)
        recView?.layoutManager = LinearLayoutManager(context)
        recView?.adapter = myfavoriteListAdapter
        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.myfavoritesLD.observe(viewLifecycleOwner, Observer {
            myfavoriteListAdapter.updateMyFavoriteList(it)
        })
        viewModel.myfavoriteLoadErrorLD.observe(viewLifecycleOwner, Observer {
            val txtError = view?.findViewById<TextView>(R.id.txtErrorMy)
            if(it == true) {
                txtError?.visibility = View.VISIBLE
            } else {
                txtError?.visibility = View.GONE
            }
        })
        viewModel.myfavoriteloadingLD.observe(viewLifecycleOwner, Observer {
            val recView = view?.findViewById<RecyclerView>(R.id.recViewMy)
            val progressLoad = view?.findViewById<ProgressBar>(R.id.progressLoadMy)
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