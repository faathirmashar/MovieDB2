package id.sharekom.moviedb.data.remote

import id.sharekom.moviedb.BuildConfig
import id.sharekom.moviedb.data.models.Movie
import id.sharekom.moviedb.data.models.MovieReviews
import id.sharekom.moviedb.data.models.Tv
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    // Popular Movies
    @GET("/3/movie/popular${BuildConfig.API_KEY}")
    suspend fun getPopularMovie(): Response<Movie>

    // Top Rated Movies
    @GET("/3/movie/top_rated${BuildConfig.API_KEY}")
    suspend fun getTopRatedMovie(): Response<Movie>

    // Upcoming Movies
    @GET("/3/movie/upcoming${BuildConfig.API_KEY}")
    suspend fun getUpcomingMovie(): Response<Movie>

    // Now Playing Movies
    @GET("/3/movie/now_playing${BuildConfig.API_KEY}")
    suspend fun getNowPlayingMovie(): Response<Movie>

    // Search Movie
    @GET("/3/search/movie${BuildConfig.API_KEY}")
    suspend fun getSearchMovie(@Query("query") searchQuery: String): Response<Movie>

    // Movie Reviews
    @GET("/3/movie/{id}/reviews${BuildConfig.API_KEY}")
    suspend fun getMovieReviews(@Path("id") id: Int): Response<MovieReviews>


    // Popular Tv
    @GET("/3/tv/popular${BuildConfig.API_KEY}")
    suspend fun getPopularTv(): Response<Tv>

    // Top Rated Tv
    @GET("/3/tv/top_rated${BuildConfig.API_KEY}")
    suspend fun getTopRatedTv(): Response<Tv>

    // On The Air Tv
    @GET("/3/tv/on_the_air${BuildConfig.API_KEY}")
    suspend fun getOnTheAirTv(): Response<Tv>

    // Airing Today Tv
    @GET("/3/tv/airing_today${BuildConfig.API_KEY}")
    suspend fun getAiringTodayTv(): Response<Tv>

    // Search Tv
    @GET("/3/search/tv${BuildConfig.API_KEY}")
    suspend fun getSearchTv(@Query("query") searchQuery: String): Response<Tv>

    // Tv Reviews
    @GET("/3/tv/{id}/reviews${BuildConfig.API_KEY}")
    suspend fun getTvReviews(@Path("id") id: Int): Response<MovieReviews>
}