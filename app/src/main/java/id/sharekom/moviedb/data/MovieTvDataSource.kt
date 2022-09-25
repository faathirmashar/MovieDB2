package id.sharekom.moviedb.data

import androidx.paging.PagingData
import id.sharekom.moviedb.data.local.MovieTvEntity
import id.sharekom.moviedb.data.models.MovieResult
import id.sharekom.moviedb.data.models.Result
import id.sharekom.moviedb.data.models.TvResult
import kotlinx.coroutines.flow.Flow

interface MovieTvDataSource {
    // Movie
    fun getPopularMovies(): Flow<PagingData<MovieResult>>
    fun getTopRatedMovie() : Flow<PagingData<MovieResult>>
    fun getUpcomingMovie() : Flow<PagingData<MovieResult>>
    fun getNowPlayingMovie() : Flow<PagingData<MovieResult>>
    fun getSearchMovie(searchQuery: String) : Flow<PagingData<MovieResult>>
    fun getMovieReviews(id: Int) : Flow<PagingData<Result>>

    // Tv
    fun getPopularTv(): Flow<PagingData<TvResult>>
    fun getTopRatedTv(): Flow<PagingData<TvResult>>
    fun getOnTheAirTv(): Flow<PagingData<TvResult>>
    fun getAiringTodayTv(): Flow<PagingData<TvResult>>
    fun getSearchTv(searchQuery: String): Flow<PagingData<TvResult>>
    fun getTvReviews(id: Int) : Flow<PagingData<Result>>

    // Favorite
    fun addToFavorite(movieTvEntity: MovieTvEntity)
    fun getAllFavorite(): Flow<PagingData<MovieTvEntity>>
    fun deleteFavorite(movieTvEntity: MovieTvEntity)
    fun getFavoriteById(id: Int): Flow<MovieTvEntity?>
}