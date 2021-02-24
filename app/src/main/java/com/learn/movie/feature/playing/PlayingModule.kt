package com.learn.movie.feature.playing

import android.content.Context
import android.util.Log
import com.learn.movie.model.Movie
import com.learn.movie.support.appconfig.AppClient
import com.learn.movie.support.appconfig.KEY_MESSAGE
import com.learn.movie.support.appconfig.KEY_RESULT
import com.learn.movie.support.extension.toList
import com.vascomm.vascwork.architecture.core.Module
import com.vascomm.vascwork.architecture.core.ViewStateInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.lang.Exception

interface IPlayingModule {
    fun listMovie()
}

class PlayingModule (context: Context, viewStateInterface: ViewStateInterface): Module(viewStateInterface, context), IPlayingModule{
    companion object {
        const val TAG_MOVIE = "tag-playing"
    }

    private val api by lazy { AppClient(context) }

    override fun listMovie() {
        scope.launch(Dispatchers.Default) {
            event(TAG_MOVIE){
                try {
                    it.loading(isLoading =  true)
                    val service = api.clientMain().listMoviePLaying().execute()
                    when(service.isSuccessful){
                        true -> {
                            val response = JSONObject(service.body()).getJSONArray(KEY_RESULT)
                            it.loading(isLoading = false)
                            Log.d("cek", "ok")
                            it.success(data = toList(Movie::class.java, response))
                        }
                        false -> {
                            val responseErr = JSONObject(service.errorBody()?.string().toString())
                            it.loading(isLoading = false)
                            Log.d("cek", "no")
                            it.failure(message = responseErr.getString(KEY_MESSAGE))
                        }
                    }

                }catch (e: Exception){
                    e.printStackTrace()
                    it.loading(isLoading = false)
                    it.failure()
                }
            }

        }
    }

}