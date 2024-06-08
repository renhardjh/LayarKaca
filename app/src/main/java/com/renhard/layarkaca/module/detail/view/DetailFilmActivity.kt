package com.renhard.layarkaca.module.detail.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.renhard.layarkaca.R
import com.renhard.layarkaca.databinding.ActivityDetailBinding
import com.renhard.layarkaca.module.detail.viewmodel.DetailFilmViewModel
import com.renhard.layarkaca.repository.local.MovieEntity
import com.renhard.layarkaca.repository.local.TVShowEntity
import com.renhard.layarkaca.utils.Constant
import com.renhard.layarkaca.utils.ViewModelFactory
import com.renhard.layarkaca.vo.FilmType
import com.renhard.layarkaca.vo.Status

class DetailFilmActivity: AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailFilmViewModel
    private var movie: MovieEntity? = null
    private var tvShow: TVShowEntity? = null
    private lateinit var type: FilmType

    companion object {
        const val EXTRA_FILM = "extra_film"
        const val EXTRA_FILM_TYPE = "extra_film_type"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailFilmViewModel::class.java]
        val extras = intent.extras
        extras?.let { bundle ->
            val filmId = bundle.getLong(EXTRA_FILM)
            type = bundle.get(EXTRA_FILM_TYPE) as FilmType

            if (type == FilmType.Movie) {
                viewModel.getMovieDetail(filmId.toString()).observe(this) { movie ->
                    if (movie != null) {
                        when (movie.status) {
                            Status.LOADING -> {
                                binding.coordinatorLayout.visibility = View.GONE
                                binding.progressBar.visibility = View.VISIBLE
                            }
                            Status.SUCCESS -> {
                                binding.coordinatorLayout.visibility = View.VISIBLE
                                binding.progressBar.visibility = View.GONE
                                this.movie = movie.data
                                populateDetailMovie()
                            }
                            Status.ERROR -> {
                                binding.coordinatorLayout.visibility = View.VISIBLE
                                binding.progressBar.visibility = View.GONE
                                Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                            }
                        }
                        viewModel.isFavorite.value = this.movie?.favorite
                    }
                }
            } else {
                viewModel.getTVShowDetail(filmId.toString()).observe(this) { tvshow ->
                    if (tvshow != null) {
                        when (tvshow.status) {
                            Status.LOADING -> {
                                binding.coordinatorLayout.visibility = View.GONE
                                binding.progressBar.visibility = View.VISIBLE
                            }
                            Status.SUCCESS -> {
                                binding.coordinatorLayout.visibility = View.VISIBLE
                                binding.progressBar.visibility = View.GONE
                                this.tvShow = tvshow.data
                                populateDetailTVShow()
                            }
                            Status.ERROR -> {
                                binding.coordinatorLayout.visibility = View.VISIBLE
                                binding.progressBar.visibility = View.GONE
                                Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                            }
                        }
                        viewModel.isFavorite.value = this.tvShow?.favorite
                    }
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        val addFavorite = menu.findItem(R.id.favorite)
        viewModel.isFavorite.observe(this) {
            addFavorite.icon = viewModel.getFavoriteIconState(this)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.favorite -> {
                if (type == FilmType.Movie) {
                    movie?.let {
                        viewModel.setMovieFavoriteState(it)
                        Toast.makeText(this, "Movie berhasil ${ if(viewModel.isFavorite.value == true) 
                            "ditambahkan ke" else "dihapus dari" } favorite kamu", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    tvShow?.let {
                        viewModel.setTVShowFavoriteState(it)
                        Toast.makeText(this, "TV Show berhasil ${ if(viewModel.isFavorite.value == true) 
                            "ditambahkan ke" else "dihapus dari" } favorite kamu", Toast.LENGTH_SHORT).show()
                    }
                }
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun populateDetailMovie() {
        binding.toolbarLayout.title = movie?.title
        binding.contentDetail.tvRating.text = movie?.voteAverage.toString()
        binding.contentDetail.tvReleaseDate.text = movie?.releaseDate
        binding.contentDetail.tvGenre.text = movie?.genres
        binding.contentDetail.tvDescription.text = movie?.overview
        Glide.with(this)
            .load(Constant.BASE_IMAGE_URL + movie?.posterPath)
            .centerCrop()
            .into(binding.ivExpandedImage)
    }

    private fun populateDetailTVShow() {
        binding.toolbarLayout.title = tvShow?.name
        binding.contentDetail.tvRating.text = tvShow?.voteAverage.toString()
        binding.contentDetail.tvReleaseDate.text = tvShow?.firstAirDate
        binding.contentDetail.tvGenre.text = tvShow?.genres
        binding.contentDetail.tvDescription.text = tvShow?.overview
        Glide.with(this)
            .load(Constant.BASE_IMAGE_URL + tvShow?.posterPath)
            .centerCrop()
            .into(binding.ivExpandedImage)
    }
}