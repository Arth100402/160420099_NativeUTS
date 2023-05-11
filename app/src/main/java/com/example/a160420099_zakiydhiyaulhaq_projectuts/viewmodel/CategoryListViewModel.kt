package com.example.a160420099_zakiydhiyaulhaq_projectuts.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.a160420099_zakiydhiyaulhaq_projectuts.model.Book
import com.example.a160420099_zakiydhiyaulhaq_projectuts.model.Category
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CategoryListViewModel(application: Application): AndroidViewModel(application) {
    val categorysLD = MutableLiveData<ArrayList<Category>>()
    val categoryLoadErrorLD = MutableLiveData<Boolean>()
    val categoryloadingLD = MutableLiveData<Boolean>()

    val TAG = "category"
    private var queue: RequestQueue? = null

    fun refresh() {
        categoryloadingLD.value = true
        categoryLoadErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/anmp/category.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Category>>() { }.type
                val result = Gson().fromJson<ArrayList<Category>>(it, sType)
                categorysLD.value = result
                categoryloadingLD.value = false

                Log.d("showcategory", result.toString())
            },
            {
                Log.d("showcategory", it.toString())
                categoryLoadErrorLD.value = true
                categoryloadingLD.value = false
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}