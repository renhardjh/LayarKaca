package com.renhard.layarkaca.module.movies.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.renhard.layarkaca.R
import com.renhard.layarkaca.databinding.ItemsMovieBinding
import com.renhard.layarkaca.module.detail.view.DetailFilmActivity
import com.renhard.layarkaca.repository.local.MovieEntity
import com.renhard.layarkaca.utils.Constant
import com.renhard.layarkaca.vo.FilmType

class MoviesAdapter : PagedListAdapter<MovieEntity, MoviesAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemsAcademyBinding = ItemsMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemsAcademyBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }

    class MovieViewHolder(private val binding: ItemsMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding) {
                tvTitle.text = movie.title
                tvOverview.text = movie.overview
                tvReleaseDate.text = movie.releaseDate
                tvRating.text = movie.voteAverage.toString()
                Glide.with(itemView.context)
                    .load(Constant.BASE_IMAGE_URL + movie.posterPath)
                    .centerCrop()
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                    .into(imgPoster)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailFilmActivity::class.java)
                    intent.putExtra(DetailFilmActivity.EXTRA_FILM, movie.id)
                    intent.putExtra(DetailFilmActivity.EXTRA_FILM_TYPE, FilmType.Movie)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}