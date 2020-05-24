package com.softlabit.mvvmproject.ui.home.movie

import com.softlabit.mvvmproject.R
import com.softlabit.mvvmproject.data.db.entities.Movie
import com.softlabit.mvvmproject.databinding.RecyclerviewMovieBinding
import com.softlabit.mvvmproject.util.toast
import com.xwray.groupie.databinding.BindableItem

class MovieItem(
    private val movie: Movie
): BindableItem<RecyclerviewMovieBinding>() {

    override fun getLayout() = R.layout.recyclerview_movie

    override fun bind(viewBinding: RecyclerviewMovieBinding, position: Int) {
        viewBinding.movie = movie

        // Click..
        viewBinding.buttonBook.setOnClickListener {
            it.context.toast("Button Book is clicked :) $position")
        }
    }

}