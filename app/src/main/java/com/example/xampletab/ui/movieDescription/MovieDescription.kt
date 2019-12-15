package com.example.xampletab.ui.movieDescription

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Menu
import com.example.cubosmovies.Home.util.APIQuerrys
import com.example.xampletab.R
import com.example.xampletab.util.getJsonExtra
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_description.*
import models.Movie

class MovieDescription : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_description)
        val movie: Movie? = intent.getJsonExtra(Movie::class.java)
        supportActionBar!!.title = movie?.title
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)

        var width = displayMetrics.widthPixels
        var height = displayMetrics.heightPixels

        movie.let {
            descText.text = movie?.overview
            val imageUrl = APIQuerrys().getBackDrop(it?.backdrop_path,"w500")
            Picasso.get().load(imageUrl).into(imageView)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.search_bar, menu)

        return  true
    }
}
