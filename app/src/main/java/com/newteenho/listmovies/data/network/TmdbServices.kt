package com.newteenho.listmovies.data.network

import com.newteenho.listmovies.data.API_KEY
import com.newteenho.listmovies.data.model.Movie
import com.newteenho.listmovies.data.network.response.MovieCreditsResponse
import com.newteenho.listmovies.data.network.response.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbServices {
    //get popular movies
    @GET("movie/popular")
    fun getPupularMovies(
        @Query("api_key") api_key: String = API_KEY,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Call<MovieResponse>

    //get one movie details
    @GET("movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movie_id : Int,
        @Query("api_key") api_key : String = API_KEY,
        @Query("language") language : String = "en-US"
    ) : Call<Movie>

    //get the recommendations for a movie
    @GET("movie/{movie_id}/recommendations")
    fun getMoviesRecommendations(
        @Path("movie_id") movie_id : Int,
        @Query("api_key") api_key : String = API_KEY,
        @Query("language") language : String = "en-US",
        @Query("page") page : Int = 1
    ): Call<MovieResponse>

    //get the credits for a movie
    @GET("movie/{movie_id}/credits")
    fun getMovieCredits(
        @Path("movie_id") movie_id : Int,
        @Query("api_key") api_key : String = API_KEY,
        @Query("language") language : String = "en-US"
    ) : Call<MovieCreditsResponse>
}