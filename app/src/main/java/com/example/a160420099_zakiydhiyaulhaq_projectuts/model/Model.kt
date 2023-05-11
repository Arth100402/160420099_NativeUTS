package com.example.a160420099_zakiydhiyaulhaq_projectuts.model

import com.google.gson.annotations.SerializedName

data class Book(
    @SerializedName("id")
    val id:String?,
    @SerializedName("judul")
    val judul:String?,
    @SerializedName("penulis")
    val penulis:String?,
    @SerializedName("publisher")
    val publisher:String?,
    @SerializedName("description")
    val description:String?,
    @SerializedName("category")
    val category:String?,
    @SerializedName("bookno")
    val bookno:String?,
    @SerializedName("pages")
    val pages:String?,
    @SerializedName("language")
    val language:String?,
    @SerializedName("photoUrl")
    val photoUrl:String?
)

data class Article (
    val id:String?,
    val judul:String?,
    val penulis:String?,
    val description:String?,
    val photoUrl:String?
)

data class Category (
    val id:String?,
    val nama:String?,
    val description:String?,
    val photoUrl:String?
)

data class Facility (
    val id:String?,
    val nama:String?,
    val tempat:String?,
    val description:String?,
    val photoUrl:String?
)

data class MyFavorite(
    @SerializedName("id")
    val id:String?,
    @SerializedName("judul")
    val judul:String?,
    @SerializedName("penulis")
    val penulis:String?,
    @SerializedName("publisher")
    val publisher:String?,
    @SerializedName("description")
    val description:String?,
    @SerializedName("category")
    val category:String?,
    @SerializedName("bookno")
    val bookno:String?,
    @SerializedName("pages")
    val pages:String?,
    @SerializedName("language")
    val language:String?,
    @SerializedName("photoUrl")
    val photoUrl:String?
)