package id.sharekom.moviedb.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.sharekom.moviedb.databinding.ActivityMainBinding
import nl.joery.animatedbottombar.AnimatedBottomBar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.vpMain.adapter = ScreenSlidePagerAdapter(this)
        binding.vpMain.setPageTransformer { _, _ ->
            binding.bottomBar.selectTabAt(binding.vpMain.currentItem)
        }

        binding.bottomBar.setOnTabSelectListener(object : AnimatedBottomBar.OnTabSelectListener{
            override fun onTabSelected(
                lastIndex: Int,
                lastTab: AnimatedBottomBar.Tab?,
                newIndex: Int,
                newTab: AnimatedBottomBar.Tab
            ) {
                binding.vpMain.currentItem = newIndex
            }
        })
    }
}