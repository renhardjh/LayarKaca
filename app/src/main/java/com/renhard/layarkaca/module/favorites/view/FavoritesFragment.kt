package com.renhard.layarkaca.module.favorites.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.renhard.layarkaca.databinding.FragmentFavoriteBinding
import com.renhard.layarkaca.module.main.PagerAdapter

class FavoritesFragment: Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            context?.let { ctx ->
                val adapter = PagerAdapter(childFragmentManager)
                adapter.addFragment(FavoriteMoviesFragment(), "Movie")
                adapter.addFragment(FavoriteTVShowFragment(), "TV Show")
                binding.viewPager.adapter = adapter
                binding.tabs.setupWithViewPager(binding.viewPager)
            }
        }
    }
}