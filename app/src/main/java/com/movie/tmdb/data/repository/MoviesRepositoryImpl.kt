package com.movie.tmdb.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.movie.tmdb.data.api.ApiResult
import com.movie.tmdb.data.api.MoviesApi
import com.movie.tmdb.data.api.handleApi
import com.movie.tmdb.data.model.Genre
import com.movie.tmdb.data.model.GenresResponse
import com.movie.tmdb.data.model.Movie
import com.movie.tmdb.data.source.MoviesPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(var moviesApi: MoviesApi) : MoviesRepository {

    override fun getPopularMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviesPagingSource(moviesApi) }
        ).flow
    }

    override suspend fun getGenres(): Flow<ApiResult<GenresResponse>> {
        return flow {
            emit(handleApi { moviesApi.getGenres() })
        }
    }
}