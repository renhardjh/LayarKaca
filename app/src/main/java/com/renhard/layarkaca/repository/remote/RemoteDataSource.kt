package com.renhard.layarkaca.repository.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.renhard.layarkaca.module.detail.model.DetailFilmResponse
import com.renhard.layarkaca.module.detail.model.ErrorResult
import com.renhard.layarkaca.module.movies.model.MovieListResponse
import com.renhard.layarkaca.module.movies.model.MovieResult
import com.renhard.layarkaca.module.tvshows.model.TVShowListResponse
import com.renhard.layarkaca.module.tvshows.model.TVShowResult
import com.renhard.layarkaca.utils.EspressoIdlingResource
import com.renhard.layarkaca.vo.FilmType
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val apiService: FilmApiService) {
    val onFailedState = MutableLiveData<ErrorResult>()

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(apiService: FilmApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                RemoteDataSource(apiService).apply { instance = this }
            }
    }

    fun getMovieList(query: String? = null): LiveData<Response<List<MovieResult>>> {
        EspressoIdlingResource.increment()
        val movieResults = MutableLiveData<Response<List<MovieResult>>>()
        val client = if(query.isNullOrEmpty()) apiService.getMovieList() else apiService.getSearchMovies(query)
        client.enqueue(object : Callback<MovieListResponse> {
            override fun onResponse(
                call: Call<MovieListResponse>,
                response: Response<MovieListResponse>
            ) {
                if (response.isSuccessful) {
                    movieResults.value = Response.success(response.body()?.results)
                } else {
                    Log.e("getMovieList", "onFailure: ${response.message()}")
                    response.errorBody()?.let {
                        movieResults.value = Response.error(response.code(), it)
                    }
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                Log.e("getMovieList", "onFailure: ${t.message.toString()}")
                onFailedState.postValue(ErrorResult("movie", t.message.toString()))
                EspressoIdlingResource.decrement()
            }
        })
        return movieResults
    }

    fun getTVShowList(query: String? = null): LiveData<Response<List<TVShowResult>>> {
        EspressoIdlingResource.increment()
        val tvResults = MutableLiveData<Response<List<TVShowResult>>>()
        val client = if(query.isNullOrEmpty()) apiService.getTVShowList() else apiService.getSearchTVShows(query)
        client.enqueue(object : Callback<TVShowListResponse> {
            override fun onResponse(
                call: Call<TVShowListResponse>,
                response: Response<TVShowListResponse>
            ) {
                if (response.isSuccessful) {
                    tvResults.value = Response.success(response.body()?.results)
                } else {
                    Log.e("getTVShowList", "onFailure: ${response.message()}")
                    response.errorBody()?.let {
                        tvResults.value = Response.error(response.code(), it)
                    }
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TVShowListResponse>, t: Throwable) {
                Log.e("getTVShowList", "onFailure: ${t.message.toString()}")
                onFailedState.postValue(ErrorResult("tvshow", t.message.toString()))
                EspressoIdlingResource.decrement()
            }
        })
        return tvResults
    }

    fun getFilmDetail(type: FilmType, id: String): LiveData<Response<DetailFilmResponse>> {
        EspressoIdlingResource.increment()
        val film = MutableLiveData<Response<DetailFilmResponse>>()
        val client = if (type == FilmType.TVShow) apiService.getDetailTVShow(id) else apiService.getDetailMovie(id)
        client.enqueue(object : Callback<DetailFilmResponse> {
            override fun onResponse(
                call: Call<DetailFilmResponse>,
                response: Response<DetailFilmResponse>
            ) {
                if (response.isSuccessful) {
                    film.value = Response.success(response.body())
                } else {
                    Log.e("getMovieDetail", "onFailure: ${response.message()}")
                    response.errorBody()?.let {
                        film.value = Response.error(response.code(), it)
                    }
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<DetailFilmResponse>, t: Throwable) {
                Log.e("getMovieDetail", "onFailure: ${t.message.toString()}")
                onFailedState.postValue(ErrorResult("detail", t.message.toString()))
                EspressoIdlingResource.decrement()
            }
        })
        return film
    }
}