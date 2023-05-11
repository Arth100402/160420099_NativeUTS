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

class FacilityListViewModel(application: Application): AndroidViewModel(application) {
    val facilitysLD = MutableLiveData<ArrayList<Facility>>()
    val facilityLoadErrorLD = MutableLiveData<Boolean>()
    val facilityloadingLD = MutableLiveData<Boolean>()

    val TAG = "facility"
    private var queue: RequestQueue? = null

    fun refresh() {
        facilityloadingLD.value = true
        facilityLoadErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/anmp/facility.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Facility>>() { }.type
                val result = Gson().fromJson<ArrayList<Facility>>(it, sType)
                facilitysLD.value = result
                facilityloadingLD.value = false

                Log.d("showfacility", result.toString())
            },
            {
                Log.d("showfacility", it.toString())
                facilityLoadErrorLD.value = true
                facilityloadingLD.value = false
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}