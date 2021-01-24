package com.newteenho.listmovies.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.newteenho.listmovies.R
import com.newteenho.listmovies.data.API_IMG_URL
import com.newteenho.listmovies.data.model.Movie
import kotlinx.android.synthetic.main.item_recycler.view.*

class MoviesAdapter(
    private val movieList: List<Movie>,
    private val onItemClickListener: ((movie: Movie) -> Unit)
) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)
        return MoviesViewHolder(itemView, onItemClickListener)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount() = movieList.size


    class MoviesViewHolder(
        itemView: View,
        private val onItemClickListener: (movie: Movie) -> Unit
    ) :
        RecyclerView.ViewHolder(itemView) {

        private val movieTitle = itemView.movieTitle
        private val movieDate = itemView.movieDate
        private val moviePoster = itemView.roundedImageView

        fun bind(movie: Movie) {

            movieTitle.text = movie.original_title
            movieDate.text = movie.release_date

            Glide
                .with(itemView)
                .load(API_IMG_URL + movie.poster_path)
                .centerCrop()
                .into(moviePoster)

            itemView.setOnClickListener {
                onItemClickListener.invoke(movie)
            }
        }
    }
}