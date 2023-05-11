package com.example.a160420099_zakiydhiyaulhaq_projectuts.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.a160420099_zakiydhiyaulhaq_projectuts.model.Facility
import com.example.a160420099_zakiydhiyaulhaq_projectuts.model.MyFavorite
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MyFavoriteListViewModel(application: Application): AndroidViewModel(application) {
    val myfavoritesLD = MutableLiveData<ArrayList<MyFavorite>>()
    val myfavoriteLoadErrorLD = MutableLiveData<Boolean>()
    val myfavoriteloadingLD = MutableLiveData<Boolean>()

    val TAG = "myfavorite"
    private var queue: RequestQueue? = null

    fun refresh() {
        myfavoriteloadingLD.value = true
        myfavoriteLoadErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/anmp/myfavorite.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<MyFavorite>>() { }.type
                val result = Gson().fromJson<ArrayList<MyFavorite>>(it, sType)
                myfavoritesLD.value = result
                myfavoriteloadingLD.value = false

                Log.d("showmyfavorite", result.toString())
            },
            {
                Log.d("showmyfavorite", it.toString())
                myfavoriteLoadErrorLD.value = true
                myfavoriteloadingLD.value = false
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}