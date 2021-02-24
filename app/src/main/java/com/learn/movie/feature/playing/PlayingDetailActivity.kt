package com.learn.movie.feature.playing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.learn.movie.R
import com.learn.movie.databinding.ActivityPlayingDetailBinding
import com.learn.movie.model.Movie
import com.learn.movie.support.appconfig.BASE_URL_IMAGE
import com.learn.movie.support.appconfig.KEY_RESULT
import com.learn.movie.support.extension.viewBinding
import kotlinx.android.synthetic.main.item_movie.view.*

class PlayingDetailActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityPlayingDetailBinding::inflate)

    private lateinit var data : Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupView()
    }

    private fun setupView() {
        data = intent?.extras?.getParcelable(KEY_RESULT)!!
        binding.imgMovie.clipToOutline = true

        Glide.with(this).load(BASE_URL_IMAGE +data.poster_path)
            .into(binding.imgMovie)
    }
}