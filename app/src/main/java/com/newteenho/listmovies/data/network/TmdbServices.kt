package com.newteenho.listmovies.data.network

import com.newteenho.listmovies.data.API_KEY
import com.newteenho.listmovies.data.network.response.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbServices {
    //get popular movies
    @GET("movie/popular")
    fun getPupularMovies(
        @Query("api_key") api_key: String = API_KEY,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Call<MovieResponse>
}