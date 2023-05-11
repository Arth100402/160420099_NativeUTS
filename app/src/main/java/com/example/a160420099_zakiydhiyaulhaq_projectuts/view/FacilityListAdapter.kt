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
import com.example.a160420099_zakiydhiyaulhaq_projectuts.util.loadImage

class FacilityListAdapter(val facilityList:ArrayList<Facility>): RecyclerView.Adapter<FacilityListAdapter.FacilityViewHolder>() {
    class FacilityViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacilityViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.facility_list_item, parent, false)
        return FacilityViewHolder(view)
    }

    override fun getItemCount(): Int {
        return facilityList.size
    }

    override fun onBindViewHolder(holder: FacilityViewHolder, position: Int) {
        val textNamaFac = holder.view.findViewById<TextView>(R.id.textNamaFac)
        val textTempatFac = holder.view.findViewById<TextView>(R.id.textTempatFac)
        val btnDetailFac = holder.view.findViewById<Button>(R.id.btnDetailFac)
        textNamaFac.text = facilityList[position].nama
        textTempatFac.text = facilityList[position].tempat
        btnDetailFac.setOnClickListener{
            val action = FacilityListFragmentDirections.actionFacilityDetail(facilityList[position].id.toString())
            Navigation.findNavController(it).navigate(action)
        }
        var imageView = holder.view.findViewById<ImageView>(R.id.imageViewFac)
        var progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBarFac)
        imageView.loadImage(facilityList[position].photoUrl, progressBar)
    }

    fun updateFacilityList(newFacilityList: ArrayList<Facility>) {
        facilityList.clear()
        facilityList.addAll(newFacilityList)
        notifyDataSetChanged()
    }
}