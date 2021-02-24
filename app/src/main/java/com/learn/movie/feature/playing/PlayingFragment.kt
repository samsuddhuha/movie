package com.learn.movie.feature.playing

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.learn.movie.R
import com.learn.movie.databinding.FragmentPlayingBinding
import com.learn.movie.model.Movie
import com.learn.movie.support.appconfig.KEY_RESULT
import com.learn.movie.support.extension.toast
import com.learn.movie.support.extension.viewBinding
import com.learn.movie.support.viewholder.MovieHolder
import com.vascomm.vascwork.architecture.adapter.RecyclerViewAdapter
import com.vascomm.vascwork.architecture.core.Result
import com.vascomm.vascwork.architecture.core.ViewStateInterface
import kotlinx.android.synthetic.main.item_movie.view.*

class PlayingFragment : Fragment(), ViewStateInterface {

    private val binding by viewBinding(FragmentPlayingBinding::bind)

    private val module by lazy { context?.let { PlayingModule(it, this) } }

    private val movieList by lazy { ArrayList<Movie>() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_playing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        module?.listMovie()

        val layoutManager = GridLayoutManager(context, 2)
        binding.rvMovie.layoutManager = layoutManager
        val adapter = object : RecyclerViewAdapter<Movie, MovieHolder>(R.layout.item_movie, MovieHolder::class.java, movieList){
            override fun bindView(holder: MovieHolder, data: Movie, position: Int) {
                context?.let { holder.bind(it, data) }
                holder.itemView.setOnClickListener {
                    val intent = Intent(context, PlayingDetailActivity::class.java)
                    val parImage = androidx.core.util.Pair<View, String>(holder.itemView.img_movie, "imgMovie")
                    val option = ActivityOptionsCompat.makeSceneTransitionAnimation(context as Activity,parImage)
                    intent.putExtra(KEY_RESULT, data)
                    startActivity(intent, option.toBundle())
                }
            }
        }
        binding.rvMovie.adapter = adapter

    }

    override fun onFailure(result: Result) {
        when(result.tag) {
            PlayingModule.TAG_MOVIE -> {
                binding.shimmerMovie.visibility = View.GONE
                binding.shimmerMovie.stopShimmerAnimation()
                toast(context!!, result.message)
            }
        }
    }

    override fun onLoading(result: Result) {
        when(result.tag) {
            PlayingModule.TAG_MOVIE -> {
                binding.shimmerMovie.visibility = View.VISIBLE
                binding.shimmerMovie.duration=500
                binding.shimmerMovie.startShimmerAnimation()
            }
        }

    }

    override fun onSuccess(result: Result) {
        when(result.tag){
            PlayingModule.TAG_MOVIE -> {
                binding.shimmerMovie.visibility = View.GONE
                binding.shimmerMovie.stopShimmerAnimation()

                val temp = result.data as ArrayList<Movie>
                movieList.clear()
                movieList.addAll(temp)
                binding.rvMovie.adapter?.notifyDataSetChanged()
            }
        }
    }
}