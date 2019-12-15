package network


import models.MovieResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface MovieService {
    @GET
    fun getMovies(@Url url: String) : Call<MovieResult>

}