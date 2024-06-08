package com.renhard.layarkaca.module.movies.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.renhard.layarkaca.databinding.FragmentMoviesBinding
import com.renhard.layarkaca.module.movies.viewmodel.MovieViewModel
import com.renhard.layarkaca.utils.ViewModelFactory
import com.renhard.layarkaca.vo.Status

class MoviesFragment : Fragment() {
    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!
    private lateinit var movieAdapter: MoviesAdapter
    lateinit var viewModel: MovieViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            val factory = ViewModelFactory.getInstance(it)
            viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

            movieAdapter = MoviesAdapter()
            binding.rvMovie.layoutManager = LinearLayoutManager(context)
            binding.rvMovie.setHasFixedSize(true)
            binding.rvMovie.adapter = movieAdapter

            viewModel.query.observe(this, {
                observeGetMovies()
            })

            observeGetMovies()
        }
    }

    private fun observeGetMovies() {
        val source = viewModel.getMovies()
        source.observe(this, { movies ->
            if (movies != null) {
                when (movies.status) {
                    Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        movies.data?.count()?.let {
                            movieAdapter.submitList(movies.data)
                            binding.progressBar.visibility = View.GONE
                            if(it > 0) {
                                binding.tvError.visibility = View.GONE
                            } else {
                                binding.tvError.visibility = View.VISIBLE
                                binding.tvError.text = "Pencarian tidak ditemukan"
                            }
                            source.removeObservers(this)
                        }
                    }
                    Status.ERROR -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        source.removeObservers(this)
                    }
                }
            }
        })
    }
}