package com.renhard.layarkaca.module.favorites.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.renhard.layarkaca.databinding.FragmentFavoriteTvshowBinding
import com.renhard.layarkaca.databinding.FragmentTvshowBinding
import com.renhard.layarkaca.module.favorites.viewmodel.FavoriteTVShowViewModel
import com.renhard.layarkaca.module.tvshows.view.TVShowsAdapter
import com.renhard.layarkaca.utils.ViewModelFactory

class FavoriteTVShowFragment : Fragment() {
    private var _binding: FragmentFavoriteTvshowBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentFavoriteTvshowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            context?.let {
                val factory = ViewModelFactory.getInstance(it)
                val viewModel = ViewModelProvider(this, factory)[FavoriteTVShowViewModel::class.java]

                val tvShowAdapter = TVShowsAdapter()
                binding.rvTvshowFavorite.layoutManager = LinearLayoutManager(context)
                binding.rvTvshowFavorite.setHasFixedSize(true)
                binding.rvTvshowFavorite.adapter = tvShowAdapter

                binding.progressBar.visibility = View.GONE
                viewModel.getFavoriteTVShows().observe(this, { tvshows ->
                    tvShowAdapter.submitList(tvshows)
                    if(tvshows.count() > 0) {
                        binding.tvError.visibility = View.GONE
                    } else {
                        binding.tvError.visibility = View.VISIBLE
                        binding.tvError.text = "Data Favorite TV Show Kosong"
                    }
                })
            }
        }
    }
}