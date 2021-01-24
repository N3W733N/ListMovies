package com.newteenho.listmovies.presentation.view.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.newteenho.listmovies.R
import com.newteenho.listmovies.presentation.view.adapter.ActorsAdapter
import com.newteenho.listmovies.presentation.view.adapter.MoviesAdapter
import com.newteenho.listmovies.presentation.viewModel.MoviesViewModel
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val viewModel: MoviesViewModel =
            ViewModelProviders.of(this).get(MoviesViewModel::class.java)

        val charid = intent.getStringExtra("CHAR_ID")
        val id = charid!!.toInt()


        id.let { viewModel.getMovieDetails(it) }//.toInt())}
        id.let { viewModel.getActorsDetails(it) }//.toInt())}
        id.let { viewModel.getRecomendations(it) }//.toInt())}
        setMovieData(viewModel)
        setActorsData(viewModel)
        setRecommendedData(viewModel)
    }

    private fun setMovieData(viewModel: MoviesViewModel) {
        viewModel.movieDetailsLD.observe(this, Observer {
            Glide
                .with(this)
                .load("https://image.tmdb.org/t/p/w500" + it.backdrop_path)
                .centerCrop()
                .into(imageViewDetails)

            textViewDetailsDate.text = it.release_date
            textViewDetailsTitle.text = it.title
            textViewMovieDescription.text = it.overview

            //Rating bar
            val ratingValue: Float = (it.vote_average / 2).toFloat()
            ratingBarDetails.numStars = 5
            ratingBarDetails.rating = ratingValue
            textViewRatingValue.text = "%.1f".format(ratingValue)
        })
    }

    private fun setActorsData(viewModel: MoviesViewModel) {
        viewModel.movieActorsLD.observe(this, Observer {
            it.let {
                with(recyclerViewActors) {
                    layoutManager = LinearLayoutManager(
                        this@DetailsActivity,
                        RecyclerView.HORIZONTAL,
                        false
                    )
                    adapter = ActorsAdapter(it)
                }
            }
        })
    }

    private fun setRecommendedData(viewModel: MoviesViewModel) {
        viewModel.movieRecomendationLD.observe(this, Observer {
            it.let {
                with(rcRelatedMovies) {
                    layoutManager = LinearLayoutManager(
                        this@DetailsActivity,
                        RecyclerView.HORIZONTAL,
                        false
                    )
                    adapter = MoviesAdapter(it) {
                        val intent =
                            DetailsActivity.getStartIntent(this@DetailsActivity, it.id.toString())
                        startActivity(intent)
                    }
                }
            }
        })
    }

    companion object {
        fun getStartIntent(
            context: Context, charID: String
        ): Intent {
            return Intent(context, DetailsActivity::class.java).apply {
                putExtra(
                    "CHAR_ID",
                    charID
                )
            }
        }
    }
}