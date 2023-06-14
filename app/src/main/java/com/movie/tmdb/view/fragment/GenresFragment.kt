package com.movie.tmdb.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.movie.tmdb.data.api.ApiResult
import com.movie.tmdb.databinding.FragmentGenresBinding
import com.movie.tmdb.view.adapter.GenresListAdapter
import com.movie.tmdb.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GenresFragment : Fragment() {

    private var genresBinding: FragmentGenresBinding? = null

    private val moviesViewModel: MainViewModel by viewModels()
    private lateinit var adapter: GenresListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        genresBinding = FragmentGenresBinding.inflate(inflater, container, false)
        return genresBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviesViewModel.getGenres()
        moviesViewModel.genreMutableLiveData.observe(viewLifecycleOwner, Observer {
            adapter = GenresListAdapter(
                it,
                object : GenresListAdapter.OnItemClick {
                    override fun onClick(id: Int?) {
                        Log.d("TAG", "onClick: id $id")
                    }
                })
            genresBinding?.rvGenres?.adapter = adapter
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        genresBinding = null
    }
}