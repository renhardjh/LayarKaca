package com.renhard.layarkaca.repository.remote

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.renhard.layarkaca.repository.local.MovieEntity
import com.renhard.layarkaca.repository.local.TVShowEntity
import com.renhard.layarkaca.vo.Resource

interface FilmDataSource {
    fun getMovieList(query: String? = null): LiveData<Resource<PagedList<MovieEntity>>>
    fun getTVShowList(query: String? = null): LiveData<Resource<PagedList<TVShowEntity>>>
    fun getMovieDetail(id: String): LiveData<Resource<MovieEntity>>
    fun getTVShowDetail(id: String): LiveData<Resource<TVShowEntity>>
    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>>
    fun getFavoriteTVShows(): LiveData<PagedList<TVShowEntity>>
    fun setMovieFavorite(movie: MovieEntity, state: Boolean)
    fun setTVShowFavorite(tvshow: TVShowEntity, state: Boolean)
}