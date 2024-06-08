package com.renhard.layarkaca.repository.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*

@Dao
interface FilmDao {
    @Query("SELECT * FROM movieentities order by title asc")
    fun getMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movieentities where title like '%' || :query || '%'")
    fun getSearchMovies(query: String): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tvshowentities order by name asc")
    fun getTVShows(): DataSource.Factory<Int, TVShowEntity>

    @Query("SELECT * FROM tvshowentities where name like '%' || :query || '%'")
    fun getSearchTVShows(query: String): DataSource.Factory<Int, TVShowEntity>

    @Transaction
    @Query("SELECT * FROM movieentities WHERE id = :id")
    fun getMovieById(id: String): LiveData<MovieEntity>

    @Transaction
    @Query("SELECT * FROM tvshowentities WHERE id = :id")
    fun getTVShowById(id: String): LiveData<TVShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTVShows(tvshows: List<TVShowEntity>)

    @Query("SELECT * FROM movieentities where favorite = 1")
    fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tvshowentities where favorite = 1")
    fun getFavoriteTVShow(): DataSource.Factory<Int, TVShowEntity>

    @Update
    fun updateMovie(course: MovieEntity)

    @Update
    fun updateTVShow(course: TVShowEntity)
}