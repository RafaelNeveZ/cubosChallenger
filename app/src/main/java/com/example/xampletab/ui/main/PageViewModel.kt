package com.example.xampletab.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import models.MovieResult

interface PageViewContract {
    interface View {
        fun showLoadingDialog()
        fun hideLoadingDialog()
        fun showError(message: String)
        fun bindMovieList(movie: MovieResult)
    }

    interface Presenter {
        fun getMovies(genero: String)
    }
}