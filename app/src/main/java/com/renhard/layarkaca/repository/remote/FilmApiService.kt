package com.renhard.layarkaca.repository.remote

import com.renhard.layarkaca.module.detail.model.DetailFilmResponse
import com.renhard.layarkaca.module.movies.model.MovieListResponse
import com.renhard.layarkaca.module.tvshows.model.TVShowListResponse
import com.renhard.layarkaca.utils.Constant
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmApiService {
    @GET("movie/popular?api_key=${Constant.APIKey}")
    fun getMovieList(): Call<MovieListResponse>

    @GET("search/movie?api_key=${Constant.APIKey}")
    fun getSearchMovies(
        @Query("query") query: String
    ): Call<MovieListResponse>

    @GET("tv/popular?api_key=${Constant.APIKey}")
    fun getTVShowList(): Call<TVShowListResponse>

    @GET("search/tv?api_key=${Constant.APIKey}")
    fun getSearchTVShows(
        @Query("query") query: String
    ): Call<TVShowListResponse>

    @GET("movie/{id}?api_key=${Constant.APIKey}")
    fun getDetailMovie(
        @Path("id") id: String
    ): Call<DetailFilmResponse>

    @GET("tv/{id}?api_key=${Constant.APIKey}")
    fun getDetailTVShow(
        @Path("id") id: String
    ): Call<DetailFilmResponse>
}