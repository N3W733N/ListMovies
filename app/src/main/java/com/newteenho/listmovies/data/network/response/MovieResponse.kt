package com.newteenho.listmovies.data.network.response

import com.newteenho.listmovies.data.model.Movie

class MovieResponse(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Long
)