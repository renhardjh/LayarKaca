package com.renhard.layarkaca.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.renhard.layarkaca.di.Injection
import com.renhard.layarkaca.module.detail.viewmodel.DetailFilmViewModel
import com.renhard.layarkaca.module.favorites.viewmodel.FavoriteMovieViewModel
import com.renhard.layarkaca.module.favorites.viewmodel.FavoriteTVShowViewModel
import com.renhard.layarkaca.module.movies.viewmodel.MovieViewModel
import com.renhard.layarkaca.module.tvshows.viewmodel.TVShowViewModel
import com.renhard.layarkaca.repository.FilmRepository

class ViewModelFactory private constructor(private val filmRepository: FilmRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                ViewModelFactory(Injection.provideFilmRepository(context)).apply { instance = this }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(filmRepository) as T
            }
            modelClass.isAssignableFrom(TVShowViewModel::class.java) -> {
                TVShowViewModel(filmRepository) as T
            }
            modelClass.isAssignableFrom(DetailFilmViewModel::class.java) -> {
                DetailFilmViewModel(filmRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteMovieViewModel::class.java) -> {
                FavoriteMovieViewModel(filmRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteTVShowViewModel::class.java) -> {
                FavoriteTVShowViewModel(filmRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}