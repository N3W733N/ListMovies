package com.newteenho.listmovies.presentation.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.newteenho.listmovies.data.model.Actors
import com.newteenho.listmovies.data.model.Movie
import com.newteenho.listmovies.data.network.ApiService
import com.newteenho.listmovies.data.network.ApiService.services
import com.newteenho.listmovies.data.network.response.MovieCreditsResponse
import com.newteenho.listmovies.data.network.response.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesViewModel : ViewModel() {
    val movieMutableLiveData: MutableLiveData<List<Movie>> = MutableLiveData()
    val movieLiveData: LiveData<List<Movie>> = movieMutableLiveData

    val movieDetailsMLD: MutableLiveData<Movie> = MutableLiveData()
    val movieDetailsLD: LiveData<Movie> = movieDetailsMLD

    val movieActorsMLD: MutableLiveData<List<Actors>> = MutableLiveData()
    val movieActorsLD: LiveData<List<Actors>> = movieActorsMLD

    val movieRecomendationMLD: MutableLiveData<List<Movie>> = MutableLiveData()
    val movieRecomendationLD: LiveData<List<Movie>> = movieRecomendationMLD

    fun getMovies() {
        ApiService.run {
            services.getPupularMovies().enqueue(object : Callback<MovieResponse> {
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            movieMutableLiveData.value = it.results
                        }

                    }
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.e("onFailure", t.message.toString())
                }
            })
        }
    }

    fun getMovieDetails(id: Int) {
        services.getMovieDetails(id).enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    response.body().let {
                        movieDetailsMLD.value = it
                    }
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun getActorsDetails(id: Int) {
        services.getMovieCredits(id).enqueue(object : Callback<MovieCreditsResponse> {
            override fun onResponse(
                call: Call<MovieCreditsResponse>,
                response: Response<MovieCreditsResponse>
            ) {
                if (response.isSuccessful) {
                    response.body().let {
                        if (it != null) {
                            movieActorsMLD.value = it.cast
                        }
                    }
                }
            }
            override fun onFailure(call: Call<MovieCreditsResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
    fun getRecomendations(id:Int){
        services.getMoviesRecommendations(id).enqueue(object : Callback<MovieResponse>{
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful){
                    response.body().let {
                        if (it != null) {
                            movieRecomendationMLD.value = it.results
                        }
                    }
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}