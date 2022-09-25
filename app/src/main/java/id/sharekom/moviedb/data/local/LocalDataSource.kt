package id.sharekom.moviedb.data.local

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class LocalDataSource constructor(private val movieDatabase: MovieDatabase) {
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    fun getMovies(): Flow<PagingData<MovieTvEntity>> {
        val resultData = Pager(
            PagingConfig(pageSize = 10)
        ) {
            movieDatabase.movieDao().getMovies()
        }.flow

        return resultData
    }

    fun getFavoriteById(id: Int): Flow<MovieTvEntity?> = movieDatabase.movieDao().favoriteById(id)

    fun insertMovie(movieEntity: MovieTvEntity){
        executorService.execute{ movieDatabase.movieDao().insertMovie(movieEntity) }
    }

    fun deleteMovie(movieEntity: MovieTvEntity){
        executorService.execute { movieDatabase.movieDao().deleteFavoriteMovie(movieEntity) }
    }
}