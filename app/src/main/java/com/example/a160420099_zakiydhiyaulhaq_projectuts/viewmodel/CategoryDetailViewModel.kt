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

class CategoryDetailViewModel(application: Application): AndroidViewModel(application) {
    val categoryLD = MutableLiveData<Category>()
    val TAG = "category"
    private var queue: RequestQueue? = null

    fun fetch(CategoryID: String) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/anmp/category.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Category>>() { }.type
                val result = Gson().fromJson<ArrayList<Category>>(it, sType)
                result.forEach{
                        C-> if (CategoryID==C.id)
                {
                    categoryLD.value = C
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