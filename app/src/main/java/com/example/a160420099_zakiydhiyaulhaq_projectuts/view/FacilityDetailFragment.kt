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
import com.example.a160420099_zakiydhiyaulhaq_projectuts.viewmodel.CategoryDetailViewModel
import com.example.a160420099_zakiydhiyaulhaq_projectuts.viewmodel.FacilityDetailViewModel

class FacilityDetailFragment : Fragment() {
    private lateinit var facilityDetailViewModel: FacilityDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_facility_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var imageViewFacDet = view.findViewById<ImageView>(R.id.imageViewFacDet)
        var textNamaFacilityDetail = view.findViewById<TextView>(R.id.textNamaFacilityDetail)
        var textTempatFacilityDetail = view.findViewById<TextView>(R.id.textTempatFacilityDetail)
        var textDescFacilityDetail = view.findViewById<TextView>(R.id.textDescFacilityDetail)
        var progressBarFacDet = view.findViewById<ProgressBar>(R.id.progressBarFacDet)
        val btnBorrowFacDet = view.findViewById<Button>(R.id.btnBorrowFacDet)


        facilityDetailViewModel = ViewModelProvider(this).get(FacilityDetailViewModel::class.java)
        facilityDetailViewModel.fetch(FacilityDetailFragmentArgs.fromBundle(requireArguments()).facilityID)

        facilityDetailViewModel.facilityLD.observe(viewLifecycleOwner){ facilityDetail ->
            imageViewFacDet.loadImage(facilityDetail.photoUrl, progressBarFacDet)
            textNamaFacilityDetail.text = facilityDetail.nama.toString()
            textTempatFacilityDetail.text = facilityDetail.tempat.toString()
            textDescFacilityDetail.text = facilityDetail.description.toString()
        }
    }
}