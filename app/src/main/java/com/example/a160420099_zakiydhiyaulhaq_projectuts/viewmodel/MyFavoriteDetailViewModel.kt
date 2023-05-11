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
import com.example.a160420099_zakiydhiyaulhaq_projectuts.model.MyFavorite
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MyFavoriteDetailViewModel(application: Application): AndroidViewModel(application) {
    val myfavoriteLD = MutableLiveData<MyFavorite>()
    val TAG = "myfavorite"
    private var queue: RequestQueue? = null

    fun fetch(MyFavoriteID: String) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/anmp/myfavorite.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<MyFavorite>>() { }.type
                val result = Gson().fromJson<ArrayList<MyFavorite>>(it, sType)
                result.forEach{
                        M-> if (MyFavoriteID==M.id)
                {
                    myfavoriteLD.value = M
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