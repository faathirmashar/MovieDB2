package id.sharekom.moviedb.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.sharekom.moviedb.BuildConfig
import id.sharekom.moviedb.R
import id.sharekom.moviedb.data.models.Result
import id.sharekom.moviedb.databinding.ItemReviewsBinding

class DetailReviewsAdapter : PagingDataAdapter<Result, DetailReviewsAdapter.DetailReviewsViewHolder>(DiffCallback) {
    object DiffCallback : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailReviewsViewHolder {
        val view = ItemReviewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailReviewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailReviewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class DetailReviewsViewHolder(private val binding: ItemReviewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(result: Result?) {
            binding.apply {
                Glide.with(root.context)
                    .load(BuildConfig.IMG_URL + result?.author_details?.avatar_path?.drop(0))
                    .placeholder(R.drawable.ic_loading)
                    .error(R.drawable.ic_broken_image)
                    .into(binding.ivReviewer)

                tvReviewerName.text = root.context.getString(R.string.reviewer_name, result?.author_details?.username)
                tvReview.text = result?.content
                tvRating.text =
                    root.context.getString(R.string.average_rating, "%.1f".format(result?.author_details?.rating))
            }
        }
    }
}