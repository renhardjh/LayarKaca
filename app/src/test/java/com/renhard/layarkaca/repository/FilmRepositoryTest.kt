package com.renhard.layarkaca.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.renhard.layarkaca.utils.DataDummy
import com.renhard.layarkaca.utils.LiveDataTestUtil
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import com.nhaarman.mockitokotlin2.verify
import com.renhard.layarkaca.repository.local.LocalDataSource
import com.renhard.layarkaca.repository.local.MovieEntity
import com.renhard.layarkaca.repository.local.TVShowEntity
import com.renhard.layarkaca.repository.remote.RemoteDataSource
import com.renhard.layarkaca.utils.AppExecutors
import com.renhard.layarkaca.utils.PagedListUtil
import com.renhard.layarkaca.vo.Resource
import org.mockito.Mockito
import org.mockito.Mockito.mock

class FilmRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val dummyMovie = DataDummy.generateDummyDetailMovie()
    private val movieId = dummyMovie.id
    private val dummyTVShow = DataDummy.generateDummyDetailTV()
    private val tvShowId = dummyTVShow.id

    private val movieResponses = DataDummy.generateRemoteDummyMovies()
    private val tvShowReponses = DataDummy.generateRemoteDummyTVShow()
    private val filmRepository = FakeFilmRepository(remote, local, appExecutors)

    @Test
    fun getMovieList() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        Mockito.`when`(local.getAllMovies()).thenReturn(dataSourceFactory)
        filmRepository.getMovieList()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getAllMovies()
        assertNotNull(movieEntities.data)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getTVShowList() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TVShowEntity>
        Mockito.`when`(local.getAllTVShows()).thenReturn(dataSourceFactory)
        filmRepository.getTVShowList()

        val tvEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateRemoteDummyTVShow()))
        verify(local).getAllTVShows()
        assertNotNull(tvEntities.data)
        assertEquals(tvShowReponses.size.toLong(), tvEntities.data?.size?.toLong())
    }

    @Test
    fun getDetailMovie() {
        val dummyEntity = MutableLiveData<MovieEntity>()
        dummyEntity.value = DataDummy.generateDummyDetailMovie()
        Mockito.`when`(local.getMovieById(movieId.toString())).thenReturn(dummyEntity)

        val movieEntity = LiveDataTestUtil.getValue(filmRepository.getMovieDetail(movieId.toString()))
        verify(local).getMovieById(movieId.toString())
        assertNotNull(movieEntity.data)
        assertNotNull(movieEntity.data?.title)
        assertEquals(movieResponses[0].title, movieEntity.data?.title)
    }

    @Test
    fun getDetailTVShow() {
        val dummyEntity = MutableLiveData<TVShowEntity>()
        dummyEntity.value = DataDummy.generateDummyDetailTV()
        Mockito.`when`(local.getTVShowById(tvShowId.toString())).thenReturn(dummyEntity)

        val tvEntity = LiveDataTestUtil.getValue(filmRepository.getTVShowDetail(tvShowId.toString()))
        verify(local).getTVShowById(tvShowId.toString())
        assertNotNull(tvEntity.data)
        assertNotNull(tvEntity.data?.name)
        assertEquals(tvShowReponses[0].name, tvEntity.data?.name)
    }

    @Test
    fun getFavoriteMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        Mockito.`when`(local.getFavoriteMovies()).thenReturn(dataSourceFactory)
        filmRepository.getFavoriteMovies()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getFavoriteMovies()
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getFavoriteTVShows() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TVShowEntity>
        Mockito.`when`(local.getFavoriteTVShows()).thenReturn(dataSourceFactory)
        filmRepository.getFavoriteTVShows()

        val tvEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateRemoteDummyTVShow()))
        verify(local).getFavoriteTVShows()
        assertNotNull(tvEntities)
        assertEquals(tvShowReponses.size.toLong(), tvEntities.data?.size?.toLong())
    }
}