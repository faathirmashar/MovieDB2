package id.sharekom.moviedb.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import id.sharekom.moviedb.data.models.MovieResult
import id.sharekom.moviedb.data.models.Result
import id.sharekom.moviedb.data.models.TvResult
import id.sharekom.moviedb.data.paging.movie.*
import id.sharekom.moviedb.data.paging.tv.*
import kotlinx.coroutines.flow.Flow

class RemoteDataSource (private val apiService: ApiService) {

    fun getPopularMovies(): Flow<PagingData<MovieResult>> {
        val resultData = Pager(
            PagingConfig(pageSize = 10)
        ) {
            PopularMoviePagingSource(apiService)
        }.flow

        return resultData
    }

    fun getTopRatedMovies(): Flow<PagingData<MovieResult>> {
        val resultData = Pager(
            PagingConfig(pageSize = 10)
        ) {
            TopRatedMoviePagingSource(apiService)
        }.flow

        return resultData
    }

    fun getUpcomingMovies(): Flow<PagingData<MovieResult>> {
        val resultData = Pager(
            PagingConfig(pageSize = 10)
        ) {
            UpComingMoviePagingSource(apiService)
        }.flow

        return resultData
    }

    fun getNowPlayingMovies(): Flow<PagingData<MovieResult>> {
        val resultData = Pager(
            PagingConfig(pageSize = 10)
        ) {
            NowPlayingMoviePagingSource(apiService)
        }.flow

        return resultData
    }

    fun getSearchMovie(searchQuery: String): Flow<PagingData<MovieResult>> {
        val resultData = Pager(
            PagingConfig(pageSize = 10)
        ) {
            SearchMoviePagingSource(apiService, searchQuery)
        }.flow

        return resultData
    }

    fun getMovieReviews(id: Int): Flow<PagingData<Result>> {
        val resultData = Pager(
            PagingConfig(pageSize = 1)
        ) {
            MovieReviewsPagingSource(apiService, id)
        }.flow

        return resultData
    }

    fun getPopularTv(): Flow<PagingData<TvResult>> {
        val resultData = Pager(
            PagingConfig(pageSize = 10)
        ) {
            PopularTvPagingSource(apiService)
        }.flow

        return resultData
    }

    fun getTopRatedTv(): Flow<PagingData<TvResult>> {
        val resultData = Pager(
            PagingConfig(pageSize = 10)
        ) {
            TopRatedTvPagingSource(apiService)
        }.flow

        return resultData
    }

    fun getOnTheAirTv(): Flow<PagingData<TvResult>> {
        val resultData = Pager(
            PagingConfig(pageSize = 10)
        ) {
            OnTheAirPagingSource(apiService)
        }.flow

        return resultData
    }

    fun getAiringTodayTv(): Flow<PagingData<TvResult>> {
        val resultData = Pager(
            PagingConfig(pageSize = 10)
        ) {
            AiringTodayPagingSource(apiService)
        }.flow

        return resultData
    }

    fun getSearchTv(searchQuery: String): Flow<PagingData<TvResult>> {
        val resultData = Pager(
            PagingConfig(pageSize = 10)
        ) {
            SearchTvPagingSource(apiService, searchQuery)
        }.flow

        return resultData
    }

    fun getTvReviews(id: Int): Flow<PagingData<Result>> {
        val resultData = Pager(
            PagingConfig(pageSize = 10)
        ) {
            TvReviewsPagingSource(apiService, id)
        }.flow

        return resultData
    }
}