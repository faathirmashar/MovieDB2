package id.sharekom.moviedb.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import id.sharekom.moviedb.data.MovieTvDataSource
import id.sharekom.moviedb.data.local.MovieTvEntity
import id.sharekom.moviedb.data.models.MovieResult
import id.sharekom.moviedb.data.models.Result
import id.sharekom.moviedb.data.models.TvResult
import kotlinx.coroutines.flow.Flow

class MovieTvViewModel(private val movieTvDataSource: MovieTvDataSource) : ViewModel() {
    // Movie
    fun getTopRatedMovies(): Flow<PagingData<MovieResult>> = movieTvDataSource.getTopRatedMovie().cachedIn(viewModelScope)
    fun getPopularMovies(): Flow<PagingData<MovieResult>> = movieTvDataSource.getPopularMovies().cachedIn(viewModelScope)
    fun getUpcomingMovies(): Flow<PagingData<MovieResult>> = movieTvDataSource.getUpcomingMovie().cachedIn(viewModelScope)
    fun getNowPlayingMovies(): Flow<PagingData<MovieResult>> = movieTvDataSource.getNowPlayingMovie().cachedIn(viewModelScope)
    fun getSearchMovie(searchQuery: String): Flow<PagingData<MovieResult>> = movieTvDataSource.getSearchMovie(searchQuery)
    fun getMovieReviews(id: Int): Flow<PagingData<Result>> = movieTvDataSource.getMovieReviews(id)

    // Tv
    fun getPopularTv(): Flow<PagingData<TvResult>> = movieTvDataSource.getPopularTv().cachedIn(viewModelScope)
    fun getTopRatedTv(): Flow<PagingData<TvResult>> = movieTvDataSource.getTopRatedTv().cachedIn(viewModelScope)
    fun getOnTheAirTv(): Flow<PagingData<TvResult>> = movieTvDataSource.getOnTheAirTv().cachedIn(viewModelScope)
    fun getAiringToday(): Flow<PagingData<TvResult>> = movieTvDataSource.getAiringTodayTv().cachedIn(viewModelScope)
    fun getSearchTv(searchQuery: String): Flow<PagingData<TvResult>> = movieTvDataSource.getSearchTv(searchQuery).cachedIn(viewModelScope)
    fun getTvReviews(id: Int): Flow<PagingData<Result>> = movieTvDataSource.getTvReviews(id)

    // Favorite
    fun addToFavorite(movieTvEntity: MovieTvEntity) {
        movieTvDataSource.addToFavorite(movieTvEntity)
    }
    fun getFavoriteById(id: Int): Flow<MovieTvEntity?> = movieTvDataSource.getFavoriteById(id)
    fun getAllFavorite(): Flow<PagingData<MovieTvEntity>> = movieTvDataSource.getAllFavorite().cachedIn(viewModelScope)
    fun deleteFavorite(moviteTvEntity: MovieTvEntity) {
        movieTvDataSource.deleteFavorite(moviteTvEntity)
    }
}