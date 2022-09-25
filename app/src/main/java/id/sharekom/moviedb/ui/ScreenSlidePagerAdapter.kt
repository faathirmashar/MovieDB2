package id.sharekom.moviedb.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import id.sharekom.moviedb.ui.movie.MovieFragment
import id.sharekom.moviedb.ui.tv.TvFragment
import id.sharekom.moviedb.ui.watch_list.WatchListFragment

class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment =
        when(position) {
            0 -> MovieFragment()
            1 -> TvFragment()
            2 -> WatchListFragment()
            else -> Fragment()
        }
}