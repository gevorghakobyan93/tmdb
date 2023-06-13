package com.movie.tmdb.view.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.movie.tmdb.R
import com.movie.tmdb.databinding.ActivityMainBinding
import com.movie.tmdb.view.fragment.GenresFragment
import com.movie.tmdb.view.fragment.MoviesFragment
import com.movie.tmdb.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setCurrentFragment(MoviesFragment())
        binding?.bottomNavigationView?.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.movies -> setCurrentFragment(MoviesFragment())
                R.id.genres -> setCurrentFragment(GenresFragment())
            }
            true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }
}