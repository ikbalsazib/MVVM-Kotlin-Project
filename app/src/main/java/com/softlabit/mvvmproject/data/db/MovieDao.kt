package com.softlabit.mvvmproject.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.softlabit.mvvmproject.data.db.entities.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllMovies(movie: List<Movie>)

    @Query("SELECT * FROM Movie")
    fun getSavedMovies() : LiveData<List<Movie>>
}