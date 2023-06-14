package com.movie.tmdb.model.repository

import androidx.paging.PagingData
import com.movie.tmdb.model.repository.api.ApiResult
import com.movie.tmdb.model.repository.api.model.GenresResponse
import com.movie.tmdb.model.repository.api.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun getPopularMovies(): Flow<PagingData<Movie>>
    suspend fun getGenres(): Flow<ApiResult<GenresResponse>>
}