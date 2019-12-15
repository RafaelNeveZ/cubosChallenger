package com.example.xampletab.ui.main


import models.MovieResult

interface MoviesListContract {
    interface View {
        fun showLoadingDialog()
        fun hideLoadingDialog()
        fun bindMovieList(movie: MovieResult)
        fun querryItens(movie: MovieResult)
    }

    interface Presenter {
        fun getMovies(genero: String, page: String)
        fun getQueryMovies(text:String, page: String)
    }
}