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

    val movieClassicMLD: MutableLiveData<List<Movie>> = MutableLiveData()
    val movieClassicLD: LiveData<List<Movie>> = movieClassicMLD

    val movieDetailsMLD: MutableLiveData<Movie> = MutableLiveData()
    val movieDetailsLD: LiveData<Movie> = movieDetailsMLD

    val movieActorsMLD: MutableLiveData<List<Actors>> = MutableLiveData()
    val movieActorsLD: LiveData<List<Actors>> = movieActorsMLD

    val movieRecommendationMLD: MutableLiveData<List<Movie>> = MutableLiveData()
    val movieRecommendationLD: LiveData<List<Movie>> = movieRecommendationMLD

    val moviesUpcomingMLD: MutableLiveData<List<Movie>> = MutableLiveData()
    val moviesUpcomingLD: LiveData<List<Movie>> = moviesUpcomingMLD

    val moviesTopRatedMLD: MutableLiveData<List<Movie>> = MutableLiveData()
    val moviesTopRatedLD: LiveData<List<Movie>> = moviesTopRatedMLD

    val moviesNowInTheatersMLD: MutableLiveData<List<Movie>> = MutableLiveData()
    val moviesNowInTheatersLD: LiveData<List<Movie>> = moviesNowInTheatersMLD

    fun viewModelExecute(){
        getMovies()
        getUpcomingMovies()
        getRatedMovies()
        getNowOnTheaters()
        getClassic()
    }

    private fun getMovies() {
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
                    Log.e("onGetMoviesFailure", t.message.toString())
                }
            })
        }
    }
    private fun getClassic(){
        ApiService.run {
            services.getMoviesClassic().enqueue(object : Callback<MovieResponse>{
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    if (response.isSuccessful){
                        response.body()?.let {
                            movieClassicMLD.value = it.results
                        }
                    }
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.e("onGetClassicFailure", t.message.toString())
                }

            })
        }
    }

    private fun getRatedMovies() {
        ApiService.run {
            services.getTopRatedMovies().enqueue(object : Callback<MovieResponse> {
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            moviesTopRatedMLD.value = it.results
                        }

                    }
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.e("onGetRatedMoviesFailure", t.message.toString())
                }
            })
        }
    }

    private fun getNowOnTheaters() {
        ApiService.run {
            services.getNowPlayingMovies().enqueue(object : Callback<MovieResponse> {
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            moviesNowInTheatersMLD.value = it.results
                        }

                    }
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.e("onGetNowOnTheateFailure", t.message.toString())
                }
            })
        }
    }

    private fun getUpcomingMovies() {
        ApiService.run {
            services.getUpcomingMovies().enqueue(object : Callback<MovieResponse> {
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            moviesUpcomingMLD.value = it.results
                        }
                    }
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.e("onGetUpComingMFailure", t.message.toString())
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
                Log.e("onGetMDetailsFailure", t.message.toString())
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
                Log.e("onGetActorsFailure", t.message.toString())
            }

        })
    }

    fun getRecommendations(id: Int) {
        services.getMoviesRecommendations(id).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    response.body().let {
                        if (it != null) {
                            movieRecommendationMLD.value = it.results
                        }
                    }
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("onGetRecomenFailure", t.message.toString())
            }

        })
    }

}