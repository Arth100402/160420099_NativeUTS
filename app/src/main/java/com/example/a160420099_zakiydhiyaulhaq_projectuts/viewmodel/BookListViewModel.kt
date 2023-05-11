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
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BookListViewModel(application: Application): AndroidViewModel(application) {
    val booksLD = MutableLiveData<ArrayList<Book>>()
    val bookLoadErrorLD = MutableLiveData<Boolean>()
    val bookloadingLD = MutableLiveData<Boolean>()

    val TAG = "book"
    private var queue: RequestQueue? = null

    fun refresh() {
        bookloadingLD.value = true
        bookLoadErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/anmp/book.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Book>>() { }.type
                val result = Gson().fromJson<ArrayList<Book>>(it, sType)
                booksLD.value = result
                bookloadingLD.value = false

                Log.d("showbook", result.toString())
            },
            {
                Log.d("showbook", it.toString())
                bookLoadErrorLD.value = true
                bookloadingLD.value = false
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}