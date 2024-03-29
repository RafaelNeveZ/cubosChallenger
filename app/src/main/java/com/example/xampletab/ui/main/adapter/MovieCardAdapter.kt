package com.example.xampletab.ui.main.adapter

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.cubosmovies.Home.util.APIQuerrys
import com.example.xampletab.R


import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item.view.*
import models.Movie


class MovieCardAdapter(var movies: ArrayList<Movie>,
                       val context: Context?,
                       val onClickItem: (view: View, movie: Movie) -> Unit) :
    RecyclerView.Adapter<MovieCardAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        )


    override fun getItemCount() = movies.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        movies[position]?.let { movie ->
            holder.bind(movie, createOnClickListener(movie))
        }
    }
    private fun createOnClickListener(movie: Movie): View.OnClickListener {
        return View.OnClickListener { view ->
            onClickItem(view, movie)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nomeView = view.title
        private val imageView:ImageView = view.poster
        private val cardView = view.itemView


        fun bind(mv: Movie, onClickListener: View.OnClickListener) {
            cardView.setOnClickListener(onClickListener)
            if(mv.title.length > 25){
                val newTitle:String =  mv.title.substring(0,25)
                nomeView.text =newTitle +"..."
            }else{
                nomeView.text = mv.title
            }

                val imageUrl = APIQuerrys().getPoster(mv.poster_path,"w500")
                Picasso.get().load(imageUrl).resize( 500,
                    750).into(imageView)


        }
    }


}