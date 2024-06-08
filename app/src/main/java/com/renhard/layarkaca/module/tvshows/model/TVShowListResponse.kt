package com.renhard.layarkaca.module.tvshows.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

data class TVShowListResponse(
    @field:SerializedName("results")
    val results: List<TVShowResult>,

    @field:SerializedName("total_pages")
    val totalPages: Int,

    @field:SerializedName("total_results")
    val totalResults: Int
)

@Entity(tableName = "tvshow_entity")
data class TVShowResult(
    @field:SerializedName("id")
    val id: Long,

    @field:SerializedName("name")
    val name: String?,

    @field:SerializedName("first_air_date")
    val firstAirDate: String?,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("overview")
    val overview: String?,

    @field:SerializedName("poster_path")
    val posterPath: String?
)
