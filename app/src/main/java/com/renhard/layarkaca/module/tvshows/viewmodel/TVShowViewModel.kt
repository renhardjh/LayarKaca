package com.renhard.layarkaca.module.tvshows.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.renhard.layarkaca.repository.FilmRepository
import com.renhard.layarkaca.repository.local.TVShowEntity
import com.renhard.layarkaca.vo.Resource

class TVShowViewModel(private val filmRepository: FilmRepository): ViewModel() {
    var query = MutableLiveData<String>()

    fun getTvShows(): LiveData<Resource<PagedList<TVShowEntity>>> = filmRepository.getTVShowList(query.value)
}