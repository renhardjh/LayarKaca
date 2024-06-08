package com.renhard.layarkaca.repository.local

import androidx.annotation.NonNull
import androidx.room.*

@Entity(tableName = "movieentities")
data class MovieEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "title")
    val title: String?,

    @ColumnInfo(name = "releaseDate")
    val releaseDate: String?,

    @ColumnInfo(name = "voteAverage")
    val voteAverage: Double,

    @ColumnInfo(name = "overview")
    val overview: String?,

    @ColumnInfo(name = "posterPath")
    val posterPath: String?,

    @ColumnInfo(name = "genres")
    var genres: String? = null,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false
)
