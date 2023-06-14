package com.movie.tmdb.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.movie.tmdb.data.api.ApiResult
import com.movie.tmdb.data.api.MoviesApi
import com.movie.tmdb.data.api.handleApi
import com.movie.tmdb.data.model.Movie

class MoviesPagingSource(
    private val service: MoviesApi
) : PagingSource<Int, Movie>() {
    private val STARTING_PAGE_INDEX = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val position = params.key ?: STARTING_PAGE_INDEX

        return when (val result = handleApi { service.getPopularMovies(position) }) {
            is ApiResult.Exception -> LoadResult.Error(Exception(result.e.message))
            is ApiResult.Error -> LoadResult.Error(Exception())
            is ApiResult.Success -> {
                LoadResult.Page(
                    data = result.data.results,
                    prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                    nextKey = if (result.data.results.isEmpty()) null else position + 1
                )
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}