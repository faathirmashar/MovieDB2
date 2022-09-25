package id.sharekom.moviedb.data.models

data class MovieReviews(
    val id: Int? = 0,
    val page: Int? = 0,
    val results: List<Result>? = ArrayList(),
    val total_pages: Int? = 0,
    val total_results: Int? = 0
)