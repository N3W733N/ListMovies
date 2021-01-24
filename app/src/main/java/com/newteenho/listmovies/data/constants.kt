package com.newteenho.listmovies.data

import kotlin.random.Random

const val API_KEY = "48590d8ff17fdfd061092c6b11d53f02"
const val API_LANGUAGE = "en-US"
const val API_PAGE = 1

fun rand(start: Int, end: Int): Int {
    require(start <= end) { "Illegal Argument" }
    return Random(System.nanoTime()).nextInt(start, end + 1)
}