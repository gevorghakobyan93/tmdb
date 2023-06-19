package com.movie.tmdb.model.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.movie.tmdb.model.repository.api.ApiResult
import com.movie.tmdb.model.repository.api.MoviesApi
import com.movie.tmdb.model.repository.api.handleApi
import com.movie.tmdb.model.repository.api.model.popular.PopularMovie

class MoviesPagingSource(
    private val service: MoviesApi
) : PagingSource<Int, PopularMovie>() {
    private val STARTING_PAGE_INDEX = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PopularMovie> {
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

    override fun getRefreshKey(state: PagingState<Int, PopularMovie>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}