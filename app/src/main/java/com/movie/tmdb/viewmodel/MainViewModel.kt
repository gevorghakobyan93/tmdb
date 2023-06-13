package com.movie.tmdb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.movie.tmdb.data.model.Movie
import com.movie.tmdb.data.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// @HiltViewModel will make models to be
// created using Hilt's model factory
// @Inject annotation used to inject all
// dependencies to view model class
@HiltViewModel
class MainViewModel @Inject constructor(private val moviesRepository: MoviesRepository) :
    ViewModel() {

    fun getPopularMovies(): Flow<PagingData<Movie>> {
        return moviesRepository.getPopularMovies()
    }
}