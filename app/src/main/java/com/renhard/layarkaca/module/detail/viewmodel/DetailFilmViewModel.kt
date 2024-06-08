package com.renhard.layarkaca.module.detail.viewmodel

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.renhard.layarkaca.R
import com.renhard.layarkaca.repository.FilmRepository
import com.renhard.layarkaca.repository.local.MovieEntity
import com.renhard.layarkaca.repository.local.TVShowEntity
import com.renhard.layarkaca.vo.Resource

class DetailFilmViewModel(private val filmRepository: FilmRepository): ViewModel() {
    val isFavorite = MutableLiveData<Boolean>()

    fun getMovieDetail(id: String): LiveData<Resource<MovieEntity>> = filmRepository.getMovieDetail(id)

    fun getTVShowDetail(id: String): LiveData<Resource<TVShowEntity>> = filmRepository.getTVShowDetail(id)

    fun setMovieFavoriteState(movie: MovieEntity) {
        val newState = !movie.favorite
        isFavorite.value = newState
        filmRepository.setMovieFavorite(movie, newState)
    }
    fun setTVShowFavoriteState(tvshow: TVShowEntity) {
        val newState = !tvshow.favorite
        isFavorite.value = newState
        filmRepository.setTVShowFavorite(tvshow, newState)
    }

    fun getFavoriteIconState(context: Context): Drawable? {
        val icon = if(isFavorite.value == true) ContextCompat.getDrawable(context, R.drawable.outline_favorite_white_24)
        else ContextCompat.getDrawable(context, R.drawable.outline_favorite_border_white_24)
        return icon
    }
}