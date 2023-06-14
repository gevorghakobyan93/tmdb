package com.movie.tmdb.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.movie.tmdb.model.repository.MoviesRepository
import com.movie.tmdb.model.repository.api.ApiResult
import com.movie.tmdb.model.repository.api.model.Genre
import com.movie.tmdb.model.repository.api.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

// @HiltViewModel will make models to be
// created using Hilt's model factory
// @Inject annotation used to inject all
// dependencies to view model class
@HiltViewModel
class MainViewModel @Inject constructor(private val moviesRepository: MoviesRepository) :
    ViewModel() {

    val genreMutableLiveData = MutableLiveData<List<Genre>>()

    var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    private val scope = viewModelScope + Job() + exceptionHandler

    fun getPopularMovies(): Flow<PagingData<Movie>> {
        return moviesRepository.getPopularMovies()
    }

    fun getGenres() {
        job = scope.launch(Dispatchers.IO) {
            moviesRepository.getGenres()
                .catch {
                    Log.d("TAG", "getGenres: exception $it")
                }
                .collect {
                    when (it) {
                        is ApiResult.Success -> genreMutableLiveData.postValue(it.data.genres)
                        is ApiResult.Error -> Log.d("TAG", "onViewCreated: error ${it.message}")
                        is ApiResult.Exception -> Log.d("TAG", "onViewCreated: exception ${it.e}")
                    }
                }
        }
    }

    private fun onError(message: String?) {
        message?.let {
            //errorData.postValue(it)
        }
    }
}