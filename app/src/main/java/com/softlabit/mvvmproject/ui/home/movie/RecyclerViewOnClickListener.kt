package com.softlabit.mvvmproject.ui.home.movie

import android.view.View
import com.softlabit.mvvmproject.data.db.entities.Movie

interface RecyclerViewOnClickListener {
    fun onRecyclerViewItemClicked(view: View, movie: Movie)
}