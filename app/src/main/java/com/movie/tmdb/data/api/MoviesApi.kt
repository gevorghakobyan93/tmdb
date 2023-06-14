package com.movie.tmdb.data.api

import com.movie.tmdb.data.model.GenresResponse
import com.movie.tmdb.data.model.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int,
        @Query("language") language: String = "en"
    ): Response<MoviesResponse>

    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("language") language: String = "en"
    ): Response<GenresResponse>
}