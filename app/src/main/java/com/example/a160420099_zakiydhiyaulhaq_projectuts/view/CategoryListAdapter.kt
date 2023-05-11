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
import com.example.a160420099_zakiydhiyaulhaq_projectuts.model.Category
import com.example.a160420099_zakiydhiyaulhaq_projectuts.util.loadImage

class CategoryListAdapter(val categoryList:ArrayList<Category>): RecyclerView.Adapter<CategoryListAdapter.CategoryViewHolder>() {
    class CategoryViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.category_list_item, parent, false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val textCategory = holder.view.findViewById<TextView>(R.id.textCategory)
        val btnMore = holder.view.findViewById<Button>(R.id.btnMore)
        textCategory.text = categoryList[position].nama
        btnMore.setOnClickListener{
            val action = CategoryListFragmentDirections.actionCategoryDetail(categoryList[position].id.toString())
            Navigation.findNavController(it).navigate(action)
        }
        var imageView = holder.view.findViewById<ImageView>(R.id.imageViewCat)
        var progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBarCat)
        imageView.loadImage(categoryList[position].photoUrl, progressBar)
    }

    fun updateCategoryList(newCategoryList: ArrayList<Category>) {
        categoryList.clear()
        categoryList.addAll(newCategoryList)
        notifyDataSetChanged()
    }
}