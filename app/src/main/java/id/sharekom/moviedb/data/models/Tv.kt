package id.sharekom.moviedb.data.models

data class Tv(
    val page: Int? = null,
    val results: ArrayList<TvResult>? = ArrayList(),
    val total_pages: Int? = 0,
    val total_results: Int? = 0
)