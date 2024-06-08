package com.renhard.layarkaca.repository.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource

class LocalDataSource private constructor(private val filmDao: FilmDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(filmDao: FilmDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(filmDao).apply {
                INSTANCE = this
            }

    }

    fun getAllMovies(query: String? = null): DataSource.Factory<Int, MovieEntity> =
        if(query.isNullOrEmpty()) filmDao.getMovies() else filmDao.getSearchMovies(query)

    fun getAllTVShows(query: String? = null): DataSource.Factory<Int, TVShowEntity> =
        if(query.isNullOrEmpty()) filmDao.getTVShows() else filmDao.getSearchTVShows(query)

    fun getMovieById(movieId: String): LiveData<MovieEntity> =
        filmDao.getMovieById(movieId)

    fun getTVShowById(tvId: String): LiveData<TVShowEntity> =
        filmDao.getTVShowById(tvId)

    fun insertMovie(movie: MovieEntity) {
        val movieList = ArrayList<MovieEntity>()
        movieList.add(movie)
        filmDao.insertMovies(movieList)
    }

    fun insertMovies(movies: List<MovieEntity>) {
        filmDao.insertMovies(movies)
    }

    fun insertTVShow(tvshow: TVShowEntity) {
        val tvList = ArrayList<TVShowEntity>()
        tvList.add(tvshow)
        filmDao.insertTVShows(tvList)
    }

    fun insertTVShows(tvshows: List<TVShowEntity>) {
        filmDao.insertTVShows(tvshows)
    }

    fun setMovieFavorite(movie: MovieEntity, newState: Boolean) {
        movie.favorite = newState
        filmDao.updateMovie(movie)
    }

    fun setTVShowFavorite(tvshow: TVShowEntity, newState: Boolean) {
        tvshow.favorite = newState
        filmDao.updateTVShow(tvshow)
    }

    fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity> =
        filmDao.getFavoriteMovies()

    fun getFavoriteTVShows(): DataSource.Factory<Int, TVShowEntity> =
        filmDao.getFavoriteTVShow()
}