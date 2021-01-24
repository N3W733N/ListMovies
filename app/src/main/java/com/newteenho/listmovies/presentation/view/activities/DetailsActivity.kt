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
import com.newteenho.listmovies.data.API_IMG_URL
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
        val charId = intent.getStringExtra("CHAR_ID")
        val id = charId!!.toInt()

        backButton.setOnClickListener { backButton() }

        id.let {
            viewModel.getMovieDetails(it)
            viewModel.getActorsDetails(it)
            viewModel.getRecommendations(it)
        }

        setMovieData(viewModel)
        setActorsData(viewModel)
        setRecommendedData(viewModel)
    }

    private fun setMovieData(viewModel: MoviesViewModel) {
        viewModel.movieDetailsLD.observe(this, Observer {
            Glide
                .with(this)
                .load(API_IMG_URL + it.backdrop_path)
                .centerCrop()
                .into(imageViewDetails)

            textViewDetailsDate.text = it.release_date
            textViewDetailsTitle.text = it.title
            textViewMovieDescription.text = it.overview

            val ratingValue: Float = (it.vote_average / 2).toFloat()
            val ratingString = "%.1f".format(ratingValue)
            ratingBarDetails.numStars = 5
            ratingBarDetails.rating = ratingValue
            textViewRatingValue.contentDescription = "Avaliação: $ratingString"
            textViewRatingValue.text = ratingString
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
        viewModel.movieRecommendationLD.observe(this, Observer {
            it.let {
                with(rcRelatedMovies) {
                    layoutManager = LinearLayoutManager(
                        this@DetailsActivity,
                        RecyclerView.HORIZONTAL,
                        false
                    )
                    adapter = MoviesAdapter(it) {
                        val intent =
                            getStartIntent(this@DetailsActivity, it.id.toString())
                        startActivity(intent)
                    }
                }
            }
        })
    }

    private fun backButton() {
        if (motionLayout.currentState == motionLayout.endState) {
            motionLayout.transitionToStart()
        } else finish()
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