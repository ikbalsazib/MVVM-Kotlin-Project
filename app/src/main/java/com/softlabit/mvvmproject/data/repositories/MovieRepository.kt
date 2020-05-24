package com.softlabit.mvvmproject.data.repositories

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.softlabit.mvvmproject.coroutine.Coroutines
import com.softlabit.mvvmproject.data.db.AppDatabase
import com.softlabit.mvvmproject.data.db.entities.Movie
import com.softlabit.mvvmproject.data.network.MyApi
import com.softlabit.mvvmproject.data.network.SafeApiRequest
import com.softlabit.mvvmproject.data.preferences.PreferencesProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

private const val MINIMUM_INTERVAL = 6

@RequiresApi(Build.VERSION_CODES.O)
class MovieRepository(
    private val api: MyApi,
    private val db: AppDatabase,
    private val prefs: PreferencesProvider
): SafeApiRequest() {
    private val movies = MutableLiveData<List<Movie>>()

    init {
        movies.observeForever {
            saveMovieToLocalDB(it)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getMovies(): LiveData<List<Movie>> {
        return withContext(Dispatchers.IO) {
            fetchAllMoviesFromServer()
            db.getMovieDao().getSavedMovies()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private suspend fun fetchAllMoviesFromServer() {
        val lastSaveAt = prefs.getLastSaveAt()
        if(lastSaveAt == null || isFetchedNeed(LocalDateTime.parse(lastSaveAt))) {
            val response = apiRequest { api.getMovies() }

            // Update Live Data..
            movies.postValue(response.movies)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun saveMovieToLocalDB(movies: List<Movie>) {
        Coroutines.io {
            prefs.saveLastSaveAt(LocalDateTime.now().toString())
            db.getMovieDao().saveAllMovies(movies)
        }
    }

    private fun isFetchedNeed(saveAt: LocalDateTime): Boolean {
        return ChronoUnit.HOURS.between(saveAt, LocalDateTime.now()) > MINIMUM_INTERVAL
    }
}