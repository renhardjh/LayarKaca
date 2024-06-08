package com.renhard.layarkaca.module.tvshows.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.renhard.layarkaca.databinding.FragmentTvshowBinding
import com.renhard.layarkaca.module.tvshows.viewmodel.TVShowViewModel
import com.renhard.layarkaca.utils.ViewModelFactory
import com.renhard.layarkaca.vo.Status

class TVShowsFragment : Fragment() {
    private var _binding: FragmentTvshowBinding? = null
    private val binding get() = _binding!!
    private lateinit var tvShowAdapter: TVShowsAdapter
    lateinit var viewModel: TVShowViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentTvshowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            context?.let {
                val factory = ViewModelFactory.getInstance(it)
                viewModel = ViewModelProvider(this, factory)[TVShowViewModel::class.java]

                tvShowAdapter = TVShowsAdapter()
                binding.rvTvshow.layoutManager = LinearLayoutManager (context)
                binding.rvTvshow.setHasFixedSize (true)
                binding.rvTvshow.adapter = tvShowAdapter

                viewModel.query.observe(this, {
                    observeGetMovies()
                })

                observeGetMovies()
            }
        }
    }

    private fun observeGetMovies() {
        val source = viewModel.getTvShows()
        source.observe(this, { tvshows ->
            if (tvshows != null) {
                when (tvshows.status) {
                    Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        tvshows.data?.count()?.let {
                            tvShowAdapter.submitList(tvshows.data)
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