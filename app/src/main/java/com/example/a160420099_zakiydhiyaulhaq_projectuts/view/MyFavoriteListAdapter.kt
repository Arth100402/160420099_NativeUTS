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
import com.example.a160420099_zakiydhiyaulhaq_projectuts.model.Facility
import com.example.a160420099_zakiydhiyaulhaq_projectuts.model.MyFavorite
import com.example.a160420099_zakiydhiyaulhaq_projectuts.util.loadImage

class MyFavoriteListAdapter(val myfavoriteList:ArrayList<MyFavorite>): RecyclerView.Adapter<MyFavoriteListAdapter.MyFavoriteViewHolder>() {
    class MyFavoriteViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyFavoriteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.my_favorite_list_item, parent, false)
        return MyFavoriteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return myfavoriteList.size
    }

    override fun onBindViewHolder(holder: MyFavoriteViewHolder, position: Int) {
        val textJudulBookMy = holder.view.findViewById<TextView>(R.id.textJudulBookMy)
        val textPenulisBookMy = holder.view.findViewById<TextView>(R.id.textPenulisBookMy)
        val textPublisherBookMy = holder.view.findViewById<TextView>(R.id.textPublisherBookMy)
        val btnDetailBookMy = holder.view.findViewById<Button>(R.id.btnDetailBookMy)
        textJudulBookMy.text = myfavoriteList[position].judul
        textPenulisBookMy.text = myfavoriteList[position].penulis
        textPublisherBookMy.text = myfavoriteList[position].publisher
        btnDetailBookMy.setOnClickListener{
            val action = MyFavoriteListFragmentDirections.actionMyFavoriteDetail(myfavoriteList[position].id.toString())
            Navigation.findNavController(it).navigate(action)
        }
        var imageView = holder.view.findViewById<ImageView>(R.id.imageViewMy)
        var progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBarMy)
        imageView.loadImage(myfavoriteList[position].photoUrl, progressBar)
    }

    fun updateMyFavoriteList(newMyFavoriteList: ArrayList<MyFavorite>) {
        myfavoriteList.clear()
        myfavoriteList.addAll(newMyFavoriteList)
        notifyDataSetChanged()
    }
}