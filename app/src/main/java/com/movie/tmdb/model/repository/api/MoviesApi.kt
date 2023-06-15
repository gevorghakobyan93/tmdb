package com.movie.tmdb.model.repository.api

import com.movie.tmdb.model.repository.api.model.GenresResponse
import com.movie.tmdb.model.repository.api.model.MovieResponse
import com.movie.tmdb.model.repository.api.model.PopularMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int,
        @Query("language") language: String = "en"
    ): Response<PopularMoviesResponse>

    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("language") language: String = "en"
    ): Response<GenresResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovie(
        @Path ("movie_id") movie_id : Int,
        @Query("language") language: String = "en"
    ): Response<MovieResponse>
}