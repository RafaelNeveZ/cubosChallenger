package com.example.xampletab.ui.main

import com.example.cubosmovies.Home.util.APIQuerrys
import models.MovieResult
import network.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PageViewPresenter : PageViewContract.Presenter {

    lateinit var view: PageViewContract.View

    override fun getMovies(genero: String) {
        view.showLoadingDialog()
        val call = RetrofitClientInstance().movieService().getMovies(APIQuerrys().getMoviesByGenre(genero))
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


}