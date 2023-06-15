package com.movie.tmdb.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.movie.tmdb.BuildConfig
import com.movie.tmdb.databinding.FragmentMovieDetailBinding
import com.movie.tmdb.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

const val ID = "movie_id"

@AndroidEntryPoint
class MovieDetailFragment : BottomSheetDialogFragment() {

    private var movieDetailBinding: FragmentMovieDetailBinding? = null

    private val moviesViewModel: MainViewModel by viewModels()
    private var id: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getInt(ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        movieDetailBinding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return movieDetailBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieDetailBinding?.ivMovieDetailClose?.setOnClickListener{dismiss()}

        moviesViewModel.getMovie(id)
        moviesViewModel.movieMutableLiveData.observe(viewLifecycleOwner) {
            movieDetailBinding?.tvMovieName?.text = it.title
            movieDetailBinding?.tvMovieOverview?.text = it.overview
            movieDetailBinding?.tvMovieReleaseDate?.text = it.releaseDate

            movieDetailBinding?.ivMoviePoster?.let { imageView ->
                Glide.with(requireContext())
                    .load(BuildConfig.IMAGE_URL + it.backdropPath)
                    .apply(RequestOptions().centerCrop())
                    .into(imageView)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        movieDetailBinding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(id: Int?) =
            MovieDetailFragment().apply {
                arguments = Bundle().apply {
                    id?.let { putInt(ID, it) }
                }
            }
    }
}