package id.sharekom.moviedb.ui.detail

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import id.sharekom.moviedb.databinding.DialogReviewBinding
import id.sharekom.moviedb.ui.MovieTvViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailReviewsDialog private constructor(context: Context, private val id: Int, private val viewModel: MovieTvViewModel, private val type: String, private val lifecycleScope: LifecycleCoroutineScope): Dialog(context) {
    private lateinit var binding: DialogReviewBinding
    private lateinit var adapter: DetailReviewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = DetailReviewsAdapter()

        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT)

        binding.pbDialogSearch.visibility = View.VISIBLE
        lifecycleScope.launch {
            if (type == "movie") {
                viewModel.getMovieReviews(id).collect {
                    binding.pbDialogSearch.visibility = View.INVISIBLE
                    adapter.submitData(it)
                }
            } else {
                viewModel.getTvReviews(id).collect {
                    binding.pbDialogSearch.visibility = View.INVISIBLE
                    adapter.submitData(it)
                }
            }
        }

        binding.btnClose.setOnClickListener { this.dismiss() }
        binding.rvSearch.layoutManager = LinearLayoutManager(context)
        binding.rvSearch.setHasFixedSize(true)
        binding.rvSearch.adapter = adapter
    }

    class Builder(private val context: Context) {
        private var id: Int = 0
        private lateinit var viewModel: MovieTvViewModel
        private lateinit var lifecycleScope: LifecycleCoroutineScope
        private lateinit var type: String

        fun setReviewerId(id: Int): Builder {
            this.id = id
            return this
        }

        fun setViewModel(viewModel: MovieTvViewModel): Builder {
            this.viewModel = viewModel
            return this
        }

        fun setViewLifecycleOwner(lifecycleScope: LifecycleCoroutineScope): Builder {
            this.lifecycleScope = lifecycleScope
            return this
        }

        fun setType(type: String): Builder {
            this.type = type
            return this
        }

        fun show() = DetailReviewsDialog(context, id, viewModel, type, lifecycleScope).show()
    }
}