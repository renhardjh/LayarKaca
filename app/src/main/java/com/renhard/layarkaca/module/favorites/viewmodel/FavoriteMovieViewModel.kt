package com.renhard.layarkaca.module.favorites.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.renhard.layarkaca.repository.FilmRepository
import com.renhard.layarkaca.repository.local.MovieEntity

class FavoriteMovieViewModel(private val filmRepository: FilmRepository): ViewModel() {
    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> = filmRepository.getFavoriteMovies()
}