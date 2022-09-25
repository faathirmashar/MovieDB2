package id.sharekom.moviedb.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieTvEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}