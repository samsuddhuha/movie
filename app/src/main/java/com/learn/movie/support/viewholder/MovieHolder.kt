package com.learn.movie.support.viewholder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.learn.movie.model.Movie
import com.learn.movie.support.appconfig.BASE_URL_IMAGE
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieHolder (itemView: View): RecyclerView.ViewHolder(itemView){

    fun bind(context: Context, data: Movie){
        Glide.with(context).load(BASE_URL_IMAGE+data.poster_path)
            .apply(RequestOptions().centerCrop())
            .into(itemView.img_movie)
        itemView.img_movie.clipToOutline = true
        itemView.tv_title.text = data.title
        itemView.tv_years.text = data.release_date?.substring(0, data.release_date?.indexOf("-")!!)
        itemView.tv_rate.text = data.vote_average.toString()
    }
}


