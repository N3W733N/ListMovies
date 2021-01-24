package com.newteenho.listmovies.presentation.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.newteenho.listmovies.R
import com.newteenho.listmovies.presentation.view.adapter.MoviesAdapter
import com.newteenho.listmovies.presentation.viewModel.MoviesViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel: MoviesViewModel =
            ViewModelProviders.of(this).get(MoviesViewModel::class.java)
        viewModel.getMovies()

        viewModel.movieLiveData.observe(this, Observer {
            it?.let {
                with(popularRecycler){
                    layoutManager = GridLayoutManager(this@MainActivity,2)
                    setHasFixedSize(true)
                    adapter = MoviesAdapter(it){
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