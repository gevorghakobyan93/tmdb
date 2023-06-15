package com.movie.tmdb.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.movie.tmdb.databinding.FragmentGenresBinding
import com.movie.tmdb.view.adapter.GenresListAdapter
import com.movie.tmdb.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

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
        moviesViewModel.genreMutableLiveData.observe(viewLifecycleOwner) {
            adapter = GenresListAdapter(
                it,
                object : GenresListAdapter.OnItemClick {
                    override fun onClick(id: Int?) {
//                        val intent = Intent().putExtra("id", id)
//                        startActivity(intent)
                    }
                })
            genresBinding?.rvGenres?.addItemDecoration(DividerItemDecoration(context, VERTICAL))
            genresBinding?.rvGenres?.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        genresBinding = null
    }

}