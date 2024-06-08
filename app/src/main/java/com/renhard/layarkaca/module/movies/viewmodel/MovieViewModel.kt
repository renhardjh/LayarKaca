package com.renhard.layarkaca.module.movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.renhard.layarkaca.repository.FilmRepository
import com.renhard.layarkaca.repository.local.MovieEntity
import com.renhard.layarkaca.vo.Resource

class MovieViewModel(private val filmRepository: FilmRepository): ViewModel() {
    var query = MutableLiveData<String>()

    fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>> = filmRepository.getMovieList(query.value)
}