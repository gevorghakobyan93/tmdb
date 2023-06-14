package com.movie.tmdb.data.repository

import androidx.paging.PagingData
import com.movie.tmdb.data.api.ApiResult
import com.movie.tmdb.data.model.GenresResponse
import com.movie.tmdb.data.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun getPopularMovies(): Flow<PagingData<Movie>>
    suspend fun getGenres(): Flow<ApiResult<GenresResponse>>
}