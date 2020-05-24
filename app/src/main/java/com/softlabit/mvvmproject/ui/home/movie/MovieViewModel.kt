package com.softlabit.mvvmproject.ui.home.movie

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.softlabit.mvvmproject.data.repositories.MovieRepository
import com.softlabit.mvvmproject.util.lazyDeferred

@RequiresApi(Build.VERSION_CODES.O)
class MovieViewModel(
    private val repository: MovieRepository
) : ViewModel() {
    val movies by lazyDeferred {
        repository.getMovies()
    }
}
