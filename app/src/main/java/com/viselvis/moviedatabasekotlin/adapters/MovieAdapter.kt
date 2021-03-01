package com.viselvis.moviedatabasekotlin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.viselvis.moviedatabasekotlin.R
import com.viselvis.moviedatabasekotlin.model.Movie

class MovieAdapter(private val movieList: List<Movie>): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    class ViewHolder(view: View):  RecyclerView.ViewHolder(view) {
        val textView: TextView

        init {
            textView = view.findViewById(R.id.tv_movieTitle)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = movieList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = movieList[position].title.toString()
    }
}