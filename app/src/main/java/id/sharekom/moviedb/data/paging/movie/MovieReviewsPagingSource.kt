package id.sharekom.moviedb.data.paging.movie

import androidx.paging.PagingSource
import androidx.paging.PagingState
import id.sharekom.moviedb.data.models.MovieResult
import id.sharekom.moviedb.data.models.MovieReviews
import id.sharekom.moviedb.data.models.Result
import id.sharekom.moviedb.data.remote.ApiService

class MovieReviewsPagingSource(private val apiService: ApiService, private val id: Int): PagingSource<Int, Result>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val currentPage = params.key ?: 1
            val response = apiService.getMovieReviews(id)
            val data = response.body()?.results ?: emptyList()

            LoadResult.Page(
                data = data,
                prevKey = if (currentPage == 1) null else -1, // Only paging forward.
                nextKey = currentPage.plus(1)
            )
        }catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return null
    }
}