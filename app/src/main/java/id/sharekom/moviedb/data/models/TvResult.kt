package id.sharekom.moviedb.data.models

data class TvResult(
    val backdrop_path: String? = null,
    val first_air_date: String? = null,
    val genre_ids: List<Int>? = ArrayList(),
    val id: Int? = 0,
    val name: String? = null,
    val origin_country: List<String>? = ArrayList(),
    val original_language: String? = null,
    val original_name: String? = null,
    val overview: String? = null,
    val popularity: Double? = 0.0,
    val poster_path: String? = null,
    val vote_average: Double? = 0.0,
    val vote_count: Int? = 0
)