package id.sharekom.moviedb.ui.tv

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.ChipGroup
import id.sharekom.moviedb.R
import id.sharekom.moviedb.data.local.MovieTvEntity
import id.sharekom.moviedb.databinding.FragmentTvBinding
import id.sharekom.moviedb.ui.MovieTvViewModel
import id.sharekom.moviedb.ui.detail.DetailTvMovieActivity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvFragment : Fragment(), TvAdapter.OnItemClickListener, ChipGroup.OnCheckedChangeListener {
    private var _binding: FragmentTvBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieTvViewModel by viewModel()
    private lateinit var adapter: TvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = TvAdapter()

        selectObserver(0)

        val tvSearchDialog = TvSearchDialog.Builder(requireContext())
            .setViewLifecycleOwner(viewLifecycleOwner.lifecycleScope)
            .setViewModel(viewModel)

        binding.tiSearch.setEndIconOnClickListener {
            tvSearchDialog.setSearchQuery(binding.tiSearch.editText?.text.toString())
            tvSearchDialog.show()
        }

        binding.chipChoiceGroup.setOnCheckedChangeListener(this)

        binding.rvTv.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTv.setHasFixedSize(true)
        adapter.setOnItemClickListener(this)
        binding.rvTv.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun selectObserver(index: Int) {
        binding.pbTv.visibility = View.VISIBLE
        when (index) {
            0 ->
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.getPopularTv().collect { results ->
                        binding.pbTv.visibility = View.INVISIBLE
                        adapter.submitData(results)
                    }
                }
            1 ->
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.getTopRatedTv().collect { results ->
                        binding.pbTv.visibility = View.INVISIBLE
                        adapter.submitData(results)
                    }
                }
            2 ->
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.getOnTheAirTv().collect { results ->
                        binding.pbTv.visibility = View.INVISIBLE
                        adapter.submitData(results)
                }
            }
            3 ->
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.getAiringToday().collect { results ->
                        binding.pbTv.visibility = View.INVISIBLE
                        adapter.submitData(results)
                }
            }
        }
    }

    override fun onCheckedChanged(group: ChipGroup?, checkedId: Int) {
        when (checkedId) {
            R.id.chip_popular -> selectObserver(0)
            R.id.chip_top_rated -> selectObserver(1)
            R.id.chip_on_the_air -> selectObserver(2)
            R.id.chip_airing_today -> selectObserver(3)
        }
    }

    override fun onItemClicked(movieTvEntity: MovieTvEntity) {
        val intent = Intent(requireContext(), DetailTvMovieActivity::class.java)

        intent.putExtra(DetailTvMovieActivity.DETAIL_DATA, movieTvEntity)

        startActivity(intent)
    }
}