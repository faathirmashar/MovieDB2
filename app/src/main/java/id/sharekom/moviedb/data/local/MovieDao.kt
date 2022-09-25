package id.sharekom.moviedb.data.local

import androidx.paging.PagingSource
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies")
    fun getMovies(): PagingSource<Int, MovieTvEntity>

    @Query("SELECT * from movies WHERE movie_id=:id")
    fun favoriteById(id: Int): Flow<MovieTvEntity?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovie(movies: MovieTvEntity)

    @Delete
    fun deleteFavoriteMovie(movie: MovieTvEntity)
}
