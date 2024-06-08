package com.renhard.layarkaca.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.renhard.layarkaca.module.detail.model.DetailFilmResponse
import com.renhard.layarkaca.module.movies.model.MovieResult
import com.renhard.layarkaca.module.tvshows.model.TVShowResult
import com.renhard.layarkaca.repository.local.LocalDataSource
import com.renhard.layarkaca.repository.local.MovieEntity
import com.renhard.layarkaca.repository.local.TVShowEntity
import com.renhard.layarkaca.repository.remote.FilmDataSource
import com.renhard.layarkaca.repository.remote.RemoteDataSource
import com.renhard.layarkaca.utils.AppExecutors
import com.renhard.layarkaca.vo.FilmType
import com.renhard.layarkaca.vo.Resource
import retrofit2.Response

class FakeFilmRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): FilmDataSource {

    override fun getMovieList(query: String?): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<MovieResult>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllMovies(query), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty() || !query.isNullOrEmpty()

            public override fun createCall(): LiveData<Response<List<MovieResult>>> =
                remoteDataSource.getMovieList(query)

            public override fun saveCallResult(data: List<MovieResult>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in data) {
                    val movie = MovieEntity(
                        response.id,
                        response.title,
                        response.releaseDate,
                        response.voteAverage,
                        response.overview,
                        response.posterPath)
                    movieList.add(movie)
                }

                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()
    }

    override fun getTVShowList(query: String?): LiveData<Resource<PagedList<TVShowEntity>>> {
        return object : NetworkBoundResource<PagedList<TVShowEntity>, List<TVShowResult>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<TVShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllTVShows(query), config).build()
            }

            override fun shouldFetch(data: PagedList<TVShowEntity>?): Boolean =
                data == null || data.isEmpty() || !query.isNullOrEmpty()

            public override fun createCall(): LiveData<Response<List<TVShowResult>>> =
                remoteDataSource.getTVShowList(query)

            public override fun saveCallResult(data: List<TVShowResult>) {
                val tvShowList = ArrayList<TVShowEntity>()
                for (response in data) {
                    val tvshow = TVShowEntity(
                        response.id,
                        response.name,
                        response.firstAirDate,
                        response.voteAverage,
                        response.overview,
                        response.posterPath)
                    tvShowList.add(tvshow)
                }

                localDataSource.insertTVShows(tvShowList)
            }
        }.asLiveData()
    }

    override fun getMovieDetail(id: String): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, DetailFilmResponse>(appExecutors) {
            public override fun loadFromDB(): LiveData<MovieEntity> =
                localDataSource.getMovieById(id)

            override fun shouldFetch(data: MovieEntity?): Boolean =
                data?.genres == null

            public override fun createCall(): LiveData<Response<DetailFilmResponse>> =
                remoteDataSource.getFilmDetail(FilmType.Movie, id)

            public override fun saveCallResult(data: DetailFilmResponse) {
                val movie = MovieEntity(
                    data.id,
                    data.title,
                    data.releaseDate,
                    data.voteAverage,
                    data.overview,
                    data.posterPath,
                    data.genres.joinToString { it.name }
                )
                localDataSource.insertMovie(movie)
            }
        }.asLiveData()
    }

    override fun getTVShowDetail(id: String): LiveData<Resource<TVShowEntity>> {
        return object : NetworkBoundResource<TVShowEntity, DetailFilmResponse>(appExecutors) {
            public override fun loadFromDB(): LiveData<TVShowEntity> =
                localDataSource.getTVShowById(id)

            override fun shouldFetch(data: TVShowEntity?): Boolean =
                data?.genres == null

            public override fun createCall(): LiveData<Response<DetailFilmResponse>> =
                remoteDataSource.getFilmDetail(FilmType.TVShow, id)

            public override fun saveCallResult(data: DetailFilmResponse) {
                val tvshow = TVShowEntity(
                    data.id,
                    data.name,
                    data.firstAirDate,
                    data.voteAverage,
                    data.overview,
                    data.posterPath,
                    data.genres.joinToString { it.name }
                )
                localDataSource.insertTVShow(tvshow)
            }
        }.asLiveData()
    }

    override fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovies(), config).build()
    }

    override fun getFavoriteTVShows(): LiveData<PagedList<TVShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteTVShows(), config).build()
    }

    override fun setMovieFavorite(movie: MovieEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setMovieFavorite(movie, state) }

    override fun setTVShowFavorite(tvshow: TVShowEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setTVShowFavorite(tvshow, state) }
}