package com.learn.movie.support.extension

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject


fun toast(context: Context, msg: String, long: Int = 0){
    Toast.makeText(context, msg, long).show()
}

inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> T) =
    lazy(LazyThreadSafetyMode.NONE) {
        bindingInflater.invoke(layoutInflater)
    }

fun <T> toList(classOfT: Class<T>, data: JSONArray):List<T>{
    val list = ArrayList<T>()
    val gson = Gson()
    (0 until data.length()).mapTo(list){
        gson.fromJson(data[it].toString(),classOfT)
    }
    return list
}

fun <T> JSONObject.toObject(classOfT: Class<T>, isInsideData: Boolean = true): T = Gson().fromJson(this.toString(), classOfT)

