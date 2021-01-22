package com.newteenho.listmovies.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {

    private fun initRetrofit() : Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val services : TmdbServices = initRetrofit().create(TmdbServices::class.java)
}