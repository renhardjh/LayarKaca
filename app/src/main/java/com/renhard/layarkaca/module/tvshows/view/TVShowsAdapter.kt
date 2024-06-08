package com.renhard.layarkaca.module.tvshows.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.renhard.layarkaca.R
import com.renhard.layarkaca.databinding.ItemsTvshowBinding
import com.renhard.layarkaca.module.detail.view.DetailFilmActivity
import com.renhard.layarkaca.repository.local.TVShowEntity
import com.renhard.layarkaca.utils.Constant
import com.renhard.layarkaca.vo.FilmType

class TVShowsAdapter : PagedListAdapter<TVShowEntity, TVShowsAdapter.TVShowViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TVShowEntity>() {
            override fun areItemsTheSame(oldItem: TVShowEntity, newItem: TVShowEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TVShowEntity, newItem: TVShowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowViewHolder {
        val itemsAcademyBinding = ItemsTvshowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TVShowViewHolder(itemsAcademyBinding)
    }

    override fun onBindViewHolder(holder: TVShowViewHolder, position: Int) {
        val tvshow = getItem(position)
        if (tvshow != null) {
            holder.bind(tvshow)
        }
    }


    class TVShowViewHolder(private val binding: ItemsTvshowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvshow: TVShowEntity) {
            with(binding) {
                tvTitle.text = tvshow.name
                tvOverview.text = tvshow.overview
                tvReleaseDate.text = tvshow.firstAirDate
                tvRating.text = tvshow.voteAverage.toString()
                Glide.with(itemView.context)
                    .load(Constant.BASE_IMAGE_URL + tvshow.posterPath)
                    .centerCrop()
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error))
                    .into(imgPoster)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailFilmActivity::class.java)
                    intent.putExtra(DetailFilmActivity.EXTRA_FILM, tvshow.id)
                    intent.putExtra(DetailFilmActivity.EXTRA_FILM_TYPE, FilmType.TVShow)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}