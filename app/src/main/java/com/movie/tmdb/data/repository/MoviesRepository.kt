package com.movie.tmdb.data.repository

import androidx.paging.PagingData
import com.movie.tmdb.data.model.Movie
import com.movie.tmdb.data.model.PopularMovies
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun getPopularMovies(): Flow<PagingData<Movie>>
}