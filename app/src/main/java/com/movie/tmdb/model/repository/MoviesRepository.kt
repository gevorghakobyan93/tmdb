package com.movie.tmdb.model.repository

import androidx.paging.PagingData
import com.movie.tmdb.model.repository.api.ApiResult
import com.movie.tmdb.model.repository.api.model.GenresResponse
import com.movie.tmdb.model.repository.api.model.MovieResponse
import com.movie.tmdb.model.repository.api.model.PopularMovie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun getPopularMovies(): Flow<PagingData<PopularMovie>>
    suspend fun getGenres(): Flow<ApiResult<GenresResponse>>
    fun getMovie(id: Int): Flow<ApiResult<MovieResponse>>
}