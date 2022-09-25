package id.sharekom.moviedb.data.paging.tv

import androidx.paging.PagingSource
import androidx.paging.PagingState
import id.sharekom.moviedb.data.models.TvResult
import id.sharekom.moviedb.data.remote.ApiService

class SearchTvPagingSource (private val apiService: ApiService, private val query: String): PagingSource<Int, TvResult>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TvResult> {
        return try {
            val currentPage = params.key ?: 1
            val response = apiService.getSearchTv(query)
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

    override fun getRefreshKey(state: PagingState<Int, TvResult>): Int? {
        return null
    }
}