package id.sharekom.moviedb.ui.movie

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import id.sharekom.moviedb.data.local.MovieTvEntity
import id.sharekom.moviedb.databinding.DialogSearchBinding
import id.sharekom.moviedb.ui.MovieTvViewModel
import id.sharekom.moviedb.ui.detail.DetailTvMovieActivity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieSearchDialog private constructor(context: Context, private val searchQuery: String, private val viewModel: MovieTvViewModel, private val lifecycleScope: LifecycleCoroutineScope): Dialog(context),
    MovieSearchAdapter.OnItemClickListener {
    private lateinit var binding: DialogSearchBinding
    private lateinit var adapter: MovieSearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MovieSearchAdapter()
        adapter.setOnItemClickListener(this)

        window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT)

        binding.pbDialogSearch.visibility = View.VISIBLE
        lifecycleScope.launch {
            viewModel.getSearchMovie(searchQuery).collect {
                binding.pbDialogSearch.visibility = View.INVISIBLE
                adapter.submitData(it)
            }
        }

        binding.btnClose.setOnClickListener { this.dismiss() }
        binding.rvSearch.layoutManager = LinearLayoutManager(context)
        binding.rvSearch.setHasFixedSize(true)
        binding.rvSearch.adapter = adapter
    }

    class Builder(private val context: Context) {
        private lateinit var searchQuery: String
        private lateinit var viewModel: MovieTvViewModel
        private lateinit var lifecycleScope: LifecycleCoroutineScope

        fun setSearchQuery(searchQuery: String): Builder {
            this.searchQuery = searchQuery
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

        fun show() = MovieSearchDialog(context, searchQuery, viewModel, lifecycleScope).show()
    }

    override fun onItemClicked(movieTvEntity: MovieTvEntity) {
        val intent = Intent(context, DetailTvMovieActivity::class.java)

        intent.putExtra(DetailTvMovieActivity.DETAIL_DATA, movieTvEntity)

        context.startActivity(intent)
    }
}