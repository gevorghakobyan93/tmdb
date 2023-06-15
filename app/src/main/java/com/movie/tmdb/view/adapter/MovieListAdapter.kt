package com.movie.tmdb.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.movie.tmdb.BuildConfig
import com.movie.tmdb.databinding.ItemMovieBinding
import com.movie.tmdb.model.repository.api.model.PopularMovie


class MovieListAdapter(private val itemClick: OnItemClick) :
    PagingDataAdapter<PopularMovie, MovieListAdapter.MovieViewHolder>(MovieListDiffCallback()) {

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
        val item: PopularMovie? = getItem(position)
        item?.let { holder.bind(it) }

        holder.itemView.setOnClickListener {
            itemClick.onClick(item?.id)
        }
    }

    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(popularMovie: PopularMovie) {
            val options: RequestOptions = RequestOptions()
                .centerCrop()

            Glide.with(binding.root)
                .load(BuildConfig.IMAGE_URL + popularMovie.backdropPath)
                .apply(options)
                .into(binding.ivMoviePoster)
        }
    }

    class MovieListDiffCallback : DiffUtil.ItemCallback<PopularMovie>() {
        override fun areItemsTheSame(oldItem: PopularMovie, newItem: PopularMovie) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: PopularMovie,
            newItem: PopularMovie
        ) = oldItem == newItem

        override fun getChangePayload(oldItem: PopularMovie, newItem: PopularMovie): Any? {
            if (oldItem != newItem) {
                return newItem
            }

            return super.getChangePayload(oldItem, newItem)
        }
    }

    interface OnItemClick {
        fun onClick(id: Int?)
    }
}