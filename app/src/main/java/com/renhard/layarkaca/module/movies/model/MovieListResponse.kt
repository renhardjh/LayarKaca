package com.renhard.layarkaca.module.movies.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @field:SerializedName("results")
    val results: List<MovieResult>,

    @field:SerializedName("total_pages")
    val totalPages: Int,

    @field:SerializedName("total_results")
    val totalResults: Int
)

@Entity(tableName = "movie_entity")
data class MovieResult(
    @field:SerializedName("id")
    val id: Long,

    @field:SerializedName("title")
    val title: String?,

    @field:SerializedName("release_date")
    val releaseDate: String?,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("overview")
    val overview: String?,

    @field:SerializedName("poster_path")
    val posterPath: String?
)
