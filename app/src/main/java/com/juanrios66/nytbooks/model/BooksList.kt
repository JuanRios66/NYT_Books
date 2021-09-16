package com.juanrios66.nytbooks.model

import com.google.gson.annotations.SerializedName

data class BooksList(
    @SerializedName("bestsellers_date")
    val bestsellersDate: String?,
    @SerializedName("books")
    val books: List<Book>?,
    @SerializedName("corrections")
    val corrections: List<Any>?,
    @SerializedName("display_name")
    val displayName: String?,
    @SerializedName("list_name")
    val listName: String?,
    @SerializedName("list_name_encoded")
    val listNameEncoded: String?,
    @SerializedName("next_published_date")
    val nextPublishedDate: String?,
    @SerializedName("normal_list_ends_at")
    val normalListEndsAt: Int?,
    @SerializedName("previous_published_date")
    val previousPublishedDate: String?,
    @SerializedName("published_date")
    val publishedDate: String?,
    @SerializedName("published_date_description")
    val publishedDateDescription: String?,
    @SerializedName("updated")
    val updated: String?
)