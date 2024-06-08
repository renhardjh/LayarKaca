package com.renhard.layarkaca.di

import android.content.Context
import com.renhard.layarkaca.repository.remote.FilmApiConfig
import com.renhard.layarkaca.repository.FilmRepository
import com.renhard.layarkaca.repository.local.FilmDatabase
import com.renhard.layarkaca.repository.local.LocalDataSource
import com.renhard.layarkaca.repository.remote.RemoteDataSource
import com.renhard.layarkaca.utils.AppExecutors

object Injection {
    fun provideFilmRepository(context: Context): FilmRepository {

        val apiService = FilmApiConfig.getApiService()
        val database = FilmDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(apiService)
        val localDataSource = LocalDataSource.getInstance(database.filmDao())
        val appExecutors = AppExecutors()

        return FilmRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}