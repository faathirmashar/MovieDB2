package id.sharekom.moviedb.di

import androidx.room.Room
import id.sharekom.moviedb.BuildConfig
import id.sharekom.moviedb.data.MovieTvDataSource
import id.sharekom.moviedb.data.MoviesTvRepository
import id.sharekom.moviedb.data.local.LocalDataSource
import id.sharekom.moviedb.data.local.MovieDatabase
import id.sharekom.moviedb.data.remote.ApiService
import id.sharekom.moviedb.data.remote.RemoteDataSource
import id.sharekom.moviedb.ui.MovieTvViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            MovieDatabase::class.java,
            "Favorites"
        ).fallbackToDestructiveMigration().build()
    }
    single { get<MovieDatabase>().movieDao() }
}

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { RemoteDataSource(get()) }
    single { LocalDataSource(get()) }
    single <MovieTvDataSource> { MoviesTvRepository(get(), get()) }
}

val viewModelModule = module {
    viewModel { MovieTvViewModel(get()) }
}

val appModule = listOf(databaseModule, networkModule, repositoryModule, viewModelModule)
