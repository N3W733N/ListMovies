package com.newteenho.listmovies.data.network.response

import com.newteenho.listmovies.data.model.Actors

class MovieCreditsResponse (
    val id : Int,
    val cast : List<Actors>
)