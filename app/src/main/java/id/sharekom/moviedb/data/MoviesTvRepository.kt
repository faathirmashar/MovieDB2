package id.sharekom.moviedb.data

import androidx.paging.PagingData
import id.sharekom.moviedb.data.local.LocalDataSource
import id.sharekom.moviedb.data.local.MovieTvEntity
import id.sharekom.moviedb.data.models.MovieResult
import id.sharekom.moviedb.data.models.Result
import id.sharekom.moviedb.data.models.TvResult
import id.sharekom.moviedb.data.remote.RemoteDataSource
import kotlinx.coroutines.flow.Flow

class MoviesTvRepository constructor(private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource): MovieTvDataSource {
    // Movies
    override fun getTopRatedMovie(): Flow<PagingData<MovieResult>> = remoteDataSource.getTopRatedMovies()
    override fun getUpcomingMovie(): Flow<PagingData<MovieResult>> = remoteDataSource.getUpcomingMovies()
    override fun getNowPlayingMovie(): Flow<PagingData<MovieResult>> = remoteDataSource.getNowPlayingMovies()
    override fun getSearchMovie(searchQuery: String): Flow<PagingData<MovieResult>> = remoteDataSource.getSearchMovie(searchQuery)
    override fun getPopularMovies(): Flow<PagingData<MovieResult>> = remoteDataSource.getPopularMovies()
    override fun getMovieReviews(id: Int): Flow<PagingData<Result>> = remoteDataSource.getMovieReviews(id)

    // TvShows
    override fun getPopularTv(): Flow<PagingData<TvResult>> = remoteDataSource.getPopularTv()
    override fun getTopRatedTv(): Flow<PagingData<TvResult>> = remoteDataSource.getTopRatedTv()
    override fun getOnTheAirTv(): Flow<PagingData<TvResult>> = remoteDataSource.getOnTheAirTv()
    override fun getAiringTodayTv(): Flow<PagingData<TvResult>> = remoteDataSource.getAiringTodayTv()
    override fun getSearchTv(searchQuery: String): Flow<PagingData<TvResult>> = remoteDataSource.getSearchTv(searchQuery)
    override fun getTvReviews(id: Int): Flow<PagingData<Result>> = remoteDataSource.getTvReviews(id)

    // Favorites
    override fun addToFavorite(movieTvEntity: MovieTvEntity) {
        localDataSource.insertMovie(movieTvEntity)
    }
    override fun getAllFavorite(): Flow<PagingData<MovieTvEntity>> = localDataSource.getMovies()
    override fun deleteFavorite(movieTvEntity: MovieTvEntity) {
        localDataSource.deleteMovie(movieTvEntity)
    }
    override fun getFavoriteById(id: Int): Flow<MovieTvEntity?> = localDataSource.getFavoriteById(id)
}