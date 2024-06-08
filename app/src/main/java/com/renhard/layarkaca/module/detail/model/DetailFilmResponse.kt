package com.renhard.layarkaca.module.detail.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "detail_film_entity")
data class DetailFilmResponse (
    @field:SerializedName("id")
    val id: Long,

    @field:SerializedName("title")
    val title: String?,

    @field:SerializedName("name")
    val name: String?,

    @field:SerializedName("release_date")
    val releaseDate: String?,

    @field:SerializedName("first_air_date")
    val firstAirDate: String?,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("genres")
    val genres: List<FilmGenre>,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("poster_path")
    val posterPath: String
)

data class FilmGenre(
    @field:SerializedName("id")
    val id: Long,

    @field:SerializedName("name")
    val name: String
)

data class ErrorResult(
    val page: String,
    val message: String
)