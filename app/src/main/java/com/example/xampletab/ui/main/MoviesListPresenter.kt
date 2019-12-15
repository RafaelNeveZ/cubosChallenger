package com.example.xampletab.ui.main

import com.example.cubosmovies.Home.util.APIQuerrys
import models.MovieResult
import network.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesListPresenter : MoviesListContract.Presenter {

    lateinit var view: MoviesListContract.View

    override fun getMovies(genero: String, page:String) {
        view.showLoadingDialog()
        val call = RetrofitClientInstance().movieService().getMovies(APIQuerrys().getMoviesByGenre(genero,page))
        call.enqueue(object : Callback<MovieResult> {
            override fun onResponse(call: Call<MovieResult>?,
                                    response: Response<MovieResult>?) {
                view.hideLoadingDialog()
                response?.body()?.let {
                    val movie: MovieResult = it
                    view.bindMovieList(movie)
                }

            }
            override fun onFailure(call: Call<MovieResult>, t: Throwable?) {
                view.hideLoadingDialog()
            }
        })
    }

    override fun getQueryMovies(text: String, page: String) {
        view.showLoadingDialog()
        val call = RetrofitClientInstance().movieService().getMovies(APIQuerrys().getQuerryedMovies(text,page))
        call.enqueue(object : Callback<MovieResult> {
            override fun onResponse(call: Call<MovieResult>?,
                                    response: Response<MovieResult>?) {
                view.hideLoadingDialog()
                response?.body()?.let {
                    val movie: MovieResult = it
                    view.querryItens(movie)
                }

            }
            override fun onFailure(call: Call<MovieResult>, t: Throwable?) {
                view.hideLoadingDialog()
            }
        })
    }

}