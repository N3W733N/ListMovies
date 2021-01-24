package com.newteenho.listmovies.presentation.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.newteenho.listmovies.R
import com.newteenho.listmovies.data.rand
import com.newteenho.listmovies.presentation.view.adapter.MoviesAdapter
import com.newteenho.listmovies.presentation.viewModel.MoviesViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel: MoviesViewModel =
            ViewModelProviders.of(this).get(MoviesViewModel::class.java)

        viewModel.viewModelExecute()

        viewModel.movieClassicLD.observe(this, Observer {
            it?.let {
                val random = rand(0, 20)
                Glide
                    .with(this@MainActivity)
                    .load("https://image.tmdb.org/t/p/w500" + it[random].poster_path)
                    .centerCrop()
                    .into(imageLatestMovie)
                textViewLatestTitle.text = it[random].title

                imageLatestMovie.setOnClickListener { view ->
                    val intent =
                        DetailsActivity.getStartIntent(this@MainActivity, it[random].id.toString())
                    this@MainActivity.startActivity(intent)
                }
            }
        })


        viewModel.movieLiveData.observe(this, Observer {
            it?.let {
                with(popularRecycler) {
                    layoutManager =
                        LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL, false)
                    setHasFixedSize(true)
                    adapter = MoviesAdapter(it) {
                        val intent = DetailsActivity.getStartIntent(
                            this@MainActivity,
                            it.id.toString()
                        )
                        this@MainActivity.startActivity(intent)
                    }
                }
            }
        })

        viewModel.moviesUpcomingLD.observe(this, Observer {
            it?.let {
                with(rcUpcomingMovies) {
                    layoutManager =
                        LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL, false)
                    setHasFixedSize(true)
                    adapter = MoviesAdapter(it) {
                        val intent = DetailsActivity.getStartIntent(
                            this@MainActivity,
                            it.id.toString()
                        )
                        this@MainActivity.startActivity(intent)
                    }
                }
            }
        })

        viewModel.moviesTopRatedLD.observe(this, Observer {
            it?.let {
                with(rcTopRated) {
                    layoutManager =
                        LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL, false)
                    setHasFixedSize(true)
                    adapter = MoviesAdapter(it) {
                        val intent = DetailsActivity.getStartIntent(
                            this@MainActivity,
                            it.id.toString()
                        )
                        this@MainActivity.startActivity(intent)
                    }
                }
            }
        })

        viewModel.moviesNowInTheatersLD.observe(this, Observer {
            it?.let {
                with(rcNowPlaying) {
                    layoutManager =
                        LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL, false)
                    setHasFixedSize(true)
                    adapter = MoviesAdapter(it) {
                        val intent = DetailsActivity.getStartIntent(
                            this@MainActivity,
                            it.id.toString()
                        )
                        this@MainActivity.startActivity(intent)
                    }
                }
            }
        })
    }
}