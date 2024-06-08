package com.renhard.layarkaca.module.favorites.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.renhard.layarkaca.repository.FilmRepository
import com.renhard.layarkaca.repository.local.TVShowEntity

class FavoriteTVShowViewModel(private val filmRepository: FilmRepository): ViewModel() {
    fun getFavoriteTVShows(): LiveData<PagedList<TVShowEntity>> = filmRepository.getFavoriteTVShows()
}
