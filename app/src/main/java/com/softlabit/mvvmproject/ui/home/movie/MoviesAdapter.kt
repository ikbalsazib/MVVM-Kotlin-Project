package com.softlabit.mvvmproject.ui.home.movie
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.databinding.DataBindingUtil
//import androidx.recyclerview.widget.RecyclerView
//import com.softlabit.mvvmproject.R
//import com.softlabit.mvvmproject.data.db.entities.Movie
//import com.softlabit.mvvmproject.databinding.RecyclerviewMovieBinding
//
//class MoviesAdapter(
//    private val movies: List<Movie>,
//    private val listener: RecyclerViewOnClickListener
//): RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder =
//        MoviesViewHolder(
//            DataBindingUtil.inflate(
//                LayoutInflater.from(parent.context),
//                R.layout.recyclerview_movie,
//                parent,
//                false
//            )
//        )
//
//    override fun getItemCount(): Int = movies.size
//
//    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
//        holder.recyclerviewMovieBinding.movie = movies[position]
//
//        // Click..
//        // Full Item Click..
////        holder.recyclerviewMovieBinding.root.setOnClickListener {
////
////        }
//
//        holder.recyclerviewMovieBinding.buttonBook.setOnClickListener {
//           listener.onRecyclerViewItemClicked(
//               holder.recyclerviewMovieBinding.buttonBook,
//               movies[position]
//           )
//        }
//
//        holder.recyclerviewMovieBinding.layoutLike.setOnClickListener {
//            listener.onRecyclerViewItemClicked(
//                holder.recyclerviewMovieBinding.layoutLike,
//                movies[position]
//            )
//        }
//    }
//
//
//    inner class MoviesViewHolder(
//        val recyclerviewMovieBinding: RecyclerviewMovieBinding
//    ): RecyclerView.ViewHolder(recyclerviewMovieBinding.root)
//}