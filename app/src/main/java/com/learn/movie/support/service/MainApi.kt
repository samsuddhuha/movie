package com.learn.movie.support.service

import com.learn.movie.support.appconfig.KEY_API
import com.learn.movie.support.appconfig.PATH_MOVIE_PLAYING
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET

interface MainApi {

    @GET("${PATH_MOVIE_PLAYING}${KEY_API}")
    fun listMoviePLaying(): Call<String>

}