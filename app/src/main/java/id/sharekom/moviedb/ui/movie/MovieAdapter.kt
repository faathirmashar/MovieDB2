package id.sharekom.moviedb.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.sharekom.moviedb.BuildConfig
import id.sharekom.moviedb.R
import id.sharekom.moviedb.data.local.MovieTvEntity
import id.sharekom.moviedb.data.models.MovieResult
import id.sharekom.moviedb.databinding.ItemMovieTvBinding

class MovieAdapter : PagingDataAdapter<MovieResult, MovieAdapter.MovieViewHolder>(DiffCallback) {
    private lateinit var onItemClickListener: OnItemClickListener

    object DiffCallback : DiffUtil.ItemCallback<MovieResult>() {
        override fun areItemsTheSame(oldItem: MovieResult, newItem: MovieResult): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieResult, newItem: MovieResult): Boolean {
            return oldItem == newItem
        }
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = ItemMovieTvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClickListener)
    }

    inner class MovieViewHolder(private val binding: ItemMovieTvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movieResult: MovieResult?, onItemClickListener: OnItemClickListener) {
            binding.apply {
                Glide.with(root.context)
                    .load(BuildConfig.IMG_URL + movieResult?.poster_path)
                    .placeholder(R.drawable.ic_loading)
                    .error(R.drawable.ic_broken_image)
                    .into(binding.image)

                title.text = root.context.getString(R.string.title, movieResult?.original_title)
                releaseDate.text =
                    root.context.getString(R.string.release_date, movieResult?.release_date)
                averageRating.text =
                    root.context.getString(R.string.average_rating, "%.1f".format(movieResult?.vote_average))

                val movieTvEntity = MovieTvEntity(
                    movieResult?.backdrop_path ?: "",
                    movieResult?.id ?: 0,
                    movieResult?.original_title ?: "",
                    movieResult?.overview ?: "",
                    movieResult?.popularity ?: 0.0,
                    movieResult?.poster_path ?: "",
                    movieResult?.release_date ?: "",
                    movieResult?.vote_average ?: 0.0,
                    movieResult?.vote_count ?: 0,
                    "movie"
                )

                root.setOnClickListener { onItemClickListener.onItemClicked(movieTvEntity) }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClicked(movieTvEntity: MovieTvEntity)
    }
}