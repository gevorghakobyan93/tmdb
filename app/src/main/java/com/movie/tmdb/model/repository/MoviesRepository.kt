package com.movie.tmdb.model.repository

import androidx.paging.PagingData
import com.movie.tmdb.model.repository.api.ApiResult
import com.movie.tmdb.model.repository.api.model.genre.GenresResponse
import com.movie.tmdb.model.repository.api.model.movie.MovieResponse
import com.movie.tmdb.model.repository.api.model.popular.PopularMovie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun getPopularMovies(): Flow<PagingData<PopularMovie>>
    suspend fun getGenres(): Flow<ApiResult<GenresResponse>>
    fun getMovie(id: Int): Flow<ApiResult<MovieResponse>>
}