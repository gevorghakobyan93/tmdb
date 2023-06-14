package com.movie.tmdb.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.movie.tmdb.R
import com.movie.tmdb.databinding.ActivityMainBinding
import com.movie.tmdb.view.fragment.GenresFragment
import com.movie.tmdb.view.fragment.MoviesFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setCurrentFragment(MoviesFragment(), "MoviesFragment")
        binding?.bottomNavigationView?.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.movies -> setCurrentFragment(
                    MoviesFragment(),
                    "MoviesFragment"
                )

                R.id.genres -> setCurrentFragment(
                    GenresFragment(),
                    "GenresFragment"
                )
            }
            true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun setCurrentFragment(fragment: Fragment, tag: String) =
        supportFragmentManager.beginTransaction()
            .replace(R.id.flFragment, fragment, tag)
            .commitAllowingStateLoss()
}