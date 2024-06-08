package com.renhard.layarkaca.module.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.renhard.layarkaca.module.detail.viewmodel.DetailFilmViewModel
import com.renhard.layarkaca.repository.FilmRepository
import com.renhard.layarkaca.repository.local.MovieEntity
import com.renhard.layarkaca.repository.local.TVShowEntity
import com.renhard.layarkaca.utils.DataDummy
import com.renhard.layarkaca.vo.Resource
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailFilmViewModelTest {
    private lateinit var viewModel: DetailFilmViewModel
    private val dummyMovie = DataDummy.generateDummyRemoteDetailMovie()
    private val movieId = dummyMovie.id
    private val dummyTVShow = DataDummy.generateDummyRemoteDetailTV()
    private val tvShowId = dummyTVShow.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var observerMovie: Observer<Resource<MovieEntity>>

    @Mock
    private lateinit var observerTVShow: Observer<Resource<TVShowEntity>>

    @Before
    fun setUp() {
        viewModel = DetailFilmViewModel(filmRepository)
    }

    @Test
    fun getDetailMovie() {
        val expected = MutableLiveData<Resource<MovieEntity>>()
        expected.value = Resource.success(dummyMovie)

        Mockito.`when`(filmRepository.getMovieDetail(movieId.toString())).thenReturn(expected)

        viewModel.getMovieDetail(movieId.toString()).observeForever(observerMovie)

        Mockito.verify(observerMovie).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getMovieDetail(movieId.toString()).value

        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun getDetailTVShow() {
        val expected = MutableLiveData<Resource<TVShowEntity>>()
        expected.value = Resource.success(dummyTVShow)

        Mockito.`when`(filmRepository.getTVShowDetail(tvShowId.toString())).thenReturn(expected)

        viewModel.getTVShowDetail(tvShowId.toString()).observeForever(observerTVShow)

        Mockito.verify(observerTVShow).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getTVShowDetail(tvShowId.toString()).value

        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun setFavoriteMovie() {
        val expected = MutableLiveData<Resource<MovieEntity>>()
        expected.value = Resource.success(dummyMovie)

        Mockito.`when`(filmRepository.getMovieDetail(movieId.toString())).thenReturn(expected)

        viewModel.setMovieFavoriteState(dummyMovie)
        viewModel.getMovieDetail(movieId.toString()).observeForever(observerMovie)

        Mockito.verify(observerMovie).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getMovieDetail(movieId.toString()).value

        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun setFavoriteTVShow() {
        val expected = MutableLiveData<Resource<TVShowEntity>>()
        expected.value = Resource.success(dummyTVShow)

        Mockito.`when`(filmRepository.getTVShowDetail(tvShowId.toString())).thenReturn(expected)

        viewModel.setTVShowFavoriteState(dummyTVShow)
        viewModel.getTVShowDetail(tvShowId.toString()).observeForever(observerTVShow)

        Mockito.verify(observerTVShow).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getTVShowDetail(tvShowId.toString()).value

        assertEquals(expectedValue, actualValue)
    }
}