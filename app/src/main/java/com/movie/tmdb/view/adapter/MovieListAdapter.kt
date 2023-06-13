package com.movie.tmdb.view.adapter

import android.R
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.movie.tmdb.BuildConfig
import com.movie.tmdb.data.model.Movie
import com.movie.tmdb.databinding.ItemMovieBinding


class MovieListAdapter :
    PagingDataAdapter<Movie, MovieListAdapter.MovieViewHolder>(MovieListDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(inflater)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MovieViewHolder,
        position: Int
    ) {
        val item: Movie? = getItem(position)
        item?.let { holder.bind(it) }
    }

    override fun onBindViewHolder(
        holder: MovieViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isNullOrEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            val newItem = payloads[0] as Movie
            holder.bind(newItem)
        }
    }

    inner class MovieViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            val options: RequestOptions = RequestOptions()
                .centerCrop()

            Glide.with(binding.root)
                .load(BuildConfig.IMAGE_URL + movie.backdropPath)
                .apply(options)
                .into(binding.ivMoviePoster)
        }
    }

    class MovieListDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Movie,
            newItem: Movie
        ) = oldItem == newItem

        override fun getChangePayload(oldItem: Movie, newItem: Movie): Any? {
            if (oldItem != newItem) {
                return newItem
            }

            return super.getChangePayload(oldItem, newItem)
        }
    }
}