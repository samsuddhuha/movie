package com.learn.movie.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class Movie(
    @SerializedName("adult") var adult: Boolean?,
    @SerializedName("backdrop_path") var backdrop_path: String?,
    @SerializedName("id") var id: Int?,
    @SerializedName("original_language") var original_language: String?,
    @SerializedName("original_title") var original_title: String?,
    @SerializedName("overview") var overview: String?,
    @SerializedName("popularity") var popularity: Float?,
    @SerializedName("poster_path") var poster_path: String?,
    @SerializedName("release_date") var release_date: String?,
    @SerializedName("title") var title: String?,
    @SerializedName("vote_average") var vote_average: Float?,
    @SerializedName("vote_count") var vote_count: Int?
) : Parcelable