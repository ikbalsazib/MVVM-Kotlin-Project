package com.softlabit.mvvmproject.data.network.responses

import com.softlabit.mvvmproject.data.db.entities.Movie

data class MovieResponse(
    val isSuccessful: Boolean?,
    val movies: List<Movie>
)