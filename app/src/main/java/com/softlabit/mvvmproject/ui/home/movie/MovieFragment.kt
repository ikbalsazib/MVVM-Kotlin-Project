package com.softlabit.mvvmproject.ui.home.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.softlabit.mvvmproject.R
import com.softlabit.mvvmproject.coroutine.Coroutines
import com.softlabit.mvvmproject.data.db.entities.Movie
import com.softlabit.mvvmproject.util.hide
import com.softlabit.mvvmproject.util.show
import com.softlabit.mvvmproject.util.toast
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.movie_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class MovieFragment : Fragment(), KodeinAware {
    // Dependency Injector..
    override val kodein by kodein()
    private val factory: MovieViewModelFactory by instance()



    private lateinit var viewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, factory).get(MovieViewModel::class.java)

        // Bind Recycler View UI with Groupee..
        bindUI()
    }


    private fun bindUI() = Coroutines.main {
        progress_bar_movie.show()
        viewModel.movies.await().observe(viewLifecycleOwner, Observer {
            progress_bar_movie.hide()
            initRecyclerView(it.toMovieItem())
        })
    }

    private fun initRecyclerView(movieItem: List<MovieItem>) {
        val mAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(movieItem)
        }

        movies_recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }
    }

    private fun List<Movie>.toMovieItem(): List<MovieItem> {
        return this.map {
            MovieItem(it)
        }
    }


}
