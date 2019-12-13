package com.example.xampletab.ui.movieDescription

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.xampletab.R
import com.example.xampletab.util.getJsonExtra
import kotlinx.android.synthetic.main.activity_movie_description.*
import models.Movie

class MovieDescription : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_description)
        val movie: Movie? = intent.getJsonExtra(Movie::class.java)
        supportActionBar!!.title = movie?.title
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        descText.text = movie?.overview

    }
}
