package com.learn.movie.support.appconfig

import android.content.Context
import com.learn.movie.support.service.MainApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

class AppClient constructor(context: Context) {
    private val log by lazy { HttpLoggingInterceptor().setLevel(okhttp3.logging.HttpLoggingInterceptor.Level.BODY) }

    fun clientMain():MainApi{
        val client  = OkHttpClient.Builder().apply {
            connectTimeout(60,TimeUnit.SECONDS)
            writeTimeout(60,TimeUnit.SECONDS)
            readTimeout(60,TimeUnit.SECONDS)
            addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .build()
                return@addInterceptor chain.proceed(request)
            }

            if(DEVELOPMENT_MODE) addInterceptor(log) ;
        }

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .client(client.build())
            .build()

        return retrofit.create(MainApi::class.java)
    }
}