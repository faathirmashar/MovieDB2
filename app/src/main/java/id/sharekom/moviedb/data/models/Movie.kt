package id.sharekom.moviedb.data.models

data class Movie(
    val page: Int? = 0,
    val results: ArrayList<MovieResult>? = ArrayList(),
    val total_pages: Int? = 0,
    val total_results: Int? = 0
)