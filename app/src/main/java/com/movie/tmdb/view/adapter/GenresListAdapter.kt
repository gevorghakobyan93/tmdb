package com.movie.tmdb.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.tmdb.data.model.Genre
import com.movie.tmdb.databinding.ItemGenreBinding


class GenresListAdapter(private val genres: List<Genre>, private val itemClick: OnItemClick) :
    RecyclerView.Adapter<GenresListAdapter.GenreViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): GenreViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemGenreBinding.inflate(inflater)
        return GenreViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return genres.size
    }

    override fun onBindViewHolder(
        holder: GenreViewHolder, position: Int
    ) {
        val item: Genre? = genres[position]
        item?.let { holder.bind(it) }

        holder.itemView.setOnClickListener {
            itemClick.onClick(item?.id)
        }
    }

    inner class GenreViewHolder(private val binding: ItemGenreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(genre: Genre) {
            binding.tvGenre.text = genre.name
        }
    }

    interface OnItemClick {
        fun onClick(id: Int?)
    }
}