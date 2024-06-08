package com.renhard.layarkaca.module.favorites.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.renhard.layarkaca.databinding.FragmentFavoriteMoviesBinding
import com.renhard.layarkaca.module.favorites.viewmodel.FavoriteMovieViewModel
import com.renhard.layarkaca.module.movies.view.MoviesAdapter
import com.renhard.layarkaca.utils.ViewModelFactory

class FavoriteMoviesFragment : Fragment() {
    private var _binding: FragmentFavoriteMoviesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentFavoriteMoviesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            val factory = ViewModelFactory.getInstance(it)
            val viewModel = ViewModelProvider(this, factory)[FavoriteMovieViewModel::class.java]

            val movieAdapter = MoviesAdapter()

            binding.rvMovieFavorite.layoutManager = LinearLayoutManager(context)
            binding.rvMovieFavorite.setHasFixedSize(true)
            binding.rvMovieFavorite.adapter = movieAdapter
            binding.progressBar.visibility = View.GONE

            viewModel.getFavoriteMovies().observe(this, { movies ->
                movieAdapter.submitList(movies)
                if (movies.count() > 0) {
                    binding.tvError.visibility = View.GONE
                } else {
                    binding.tvError.visibility = View.VISIBLE
                    binding.tvError.text = "Data Favorite Movie Kosong"
                }
            })
        }
    }
}