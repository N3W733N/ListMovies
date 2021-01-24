package com.newteenho.listmovies.data.network

import com.newteenho.listmovies.data.API_KEY
import com.newteenho.listmovies.data.API_LANGUAGE
import com.newteenho.listmovies.data.API_PAGE
import com.newteenho.listmovies.data.model.Movie
import com.newteenho.listmovies.data.network.response.MovieCreditsResponse
import com.newteenho.listmovies.data.network.response.MovieResponse
import com.newteenho.listmovies.data.rand
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbServices {

    @GET("discover/movie")
    fun getMoviesClassic(
        @Query("api_key") api_key: String = API_KEY,
        @Query("language") language: String = "en-US",
        @Query("sort_by") sort_by: String = "popularity.desc",
        @Query("include_adult") include_adult: Boolean = false,
        @Query("include_video") include_video: Boolean = false,
        @Query("page") page: Int = 1,
        @Query("primary_release_year") primary_release_year: Int = rand(1960, 1980)
    ): Call<MovieResponse>

    //get popular movies
    @GET("movie/popular")
    fun getPupularMovies(
        @Query("api_key") api_key: String = API_KEY,
        @Query("language") language: String = API_LANGUAGE,
        @Query("page") page: Int = API_PAGE
    ): Call<MovieResponse>

    //get one movie details
    @GET("movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String = API_KEY,
        @Query("language") language: String = API_LANGUAGE
    ): Call<Movie>

    //get the recommendations for a movie
    @GET("movie/{movie_id}/recommendations")
    fun getMoviesRecommendations(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String = API_KEY,
        @Query("language") language: String = API_LANGUAGE,
        @Query("page") page: Int = API_PAGE
    ): Call<MovieResponse>

    //get the credits for a movie
    @GET("movie/{movie_id}/credits")
    fun getMovieCredits(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String = API_KEY,
        @Query("language") language: String = API_LANGUAGE
    ): Call<MovieCreditsResponse>

    // get upcoming movies
    @GET("movie/upcoming")
    fun getUpcomingMovies(
        @Query("api_key") api_key: String = API_KEY,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Call<MovieResponse>

    //get top rated movies
    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key") api_key: String = API_KEY,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Call<MovieResponse>

    //get now playing movies
    @GET("movie/now_playing")
    fun getNowPlayingMovies(
        @Query("api_key") api_key: String = API_KEY,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Call<MovieResponse>
}