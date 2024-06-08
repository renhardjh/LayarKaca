package com.renhard.layarkaca.module.favorites

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.renhard.layarkaca.module.tvshows.viewmodel.TVShowViewModel
import com.renhard.layarkaca.repository.FilmRepository
import com.renhard.layarkaca.repository.local.TVShowEntity
import com.renhard.layarkaca.utils.DataDummy
import com.renhard.layarkaca.vo.Resource
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executors

@RunWith(MockitoJUnitRunner::class)
class FavoriteTVShowViewModelTest {
    private lateinit var viewModel: TVShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TVShowEntity>>>

    @Before
    fun setUp() {
        viewModel = TVShowViewModel(filmRepository)
    }

    @Test
    fun getTVShows() {
        val tvshows = PagedTestDataSources.snapshot(DataDummy.generateDummyTVShow())
        val expected = MutableLiveData<Resource<PagedList<TVShowEntity>>>()
        expected.value = Resource.success(tvshows)

        Mockito.`when`(filmRepository.getTVShowList()).thenReturn(expected)

        viewModel.getTvShows().observeForever(observer)
        Mockito.verify(observer).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getTvShows().value
        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.data, actualValue?.data)
        assertEquals(expectedValue?.data?.size, actualValue?.data?.size)
    }

    @Test
    fun getTVShowsDataEmpty() {
        val tvShows = PagedTestDataSources.snapshot()
        val expected = MutableLiveData<Resource<PagedList<TVShowEntity>>>()
        expected.value = Resource.success(tvShows)

        Mockito.`when`(filmRepository.getTVShowList()).thenReturn(expected)

        viewModel.getTvShows().observeForever(observer)
        Mockito.verify(observer).onChanged(expected.value)

        val actualValueDataSize = viewModel.getTvShows().value?.data?.size
        Assert.assertTrue(
            "size of data should be 0, actual is $actualValueDataSize",
            actualValueDataSize == 0
        )
    }

    class PagedTestDataSources private constructor(private val items: List<TVShowEntity>) : PositionalDataSource<TVShowEntity>() {
        companion object {
            fun snapshot(items: List<TVShowEntity> = listOf()): PagedList<TVShowEntity> {
                return PagedList.Builder(PagedTestDataSources(items), 10)
                    .setNotifyExecutor(Executors.newSingleThreadExecutor())
                    .setFetchExecutor(Executors.newSingleThreadExecutor())
                    .build()
            }
        }

        override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<TVShowEntity>) {
            callback.onResult(items, 0, items.size)
        }

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<TVShowEntity>) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(items.subList(start, end))
        }
    }
}