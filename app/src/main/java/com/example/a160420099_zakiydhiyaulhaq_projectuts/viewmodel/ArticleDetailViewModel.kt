package com.example.a160420099_zakiydhiyaulhaq_projectuts.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.a160420099_zakiydhiyaulhaq_projectuts.model.Article
import com.example.a160420099_zakiydhiyaulhaq_projectuts.model.Book
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ArticleDetailViewModel(application: Application): AndroidViewModel(application) {
    val articleLD = MutableLiveData<Article>()
    val TAG = "article"
    private var queue: RequestQueue? = null

    fun fetch(ArticleID: String) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/anmp/article.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Article>>() { }.type
                val result = Gson().fromJson<ArrayList<Article>>(it, sType)
                result.forEach{
                        A-> if (ArticleID==A.id)
                {
                    articleLD.value = A
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