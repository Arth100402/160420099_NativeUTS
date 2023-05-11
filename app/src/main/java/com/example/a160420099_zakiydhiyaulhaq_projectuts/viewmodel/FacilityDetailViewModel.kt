package com.example.a160420099_zakiydhiyaulhaq_projectuts.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.a160420099_zakiydhiyaulhaq_projectuts.model.Category
import com.example.a160420099_zakiydhiyaulhaq_projectuts.model.Facility
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FacilityDetailViewModel(application: Application): AndroidViewModel(application) {
    val facilityLD = MutableLiveData<Facility>()
    val TAG = "facility"
    private var queue: RequestQueue? = null

    fun fetch(FacilityID: String) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/anmp/facility.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Facility>>() { }.type
                val result = Gson().fromJson<ArrayList<Facility>>(it, sType)
                result.forEach{
                        F-> if (FacilityID==F.id)
                {
                    facilityLD.value = F
                }
                }

                Log.d("showvoley", result.toString())
            },
            {
                Log.d("showvoley", it.toString())
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}