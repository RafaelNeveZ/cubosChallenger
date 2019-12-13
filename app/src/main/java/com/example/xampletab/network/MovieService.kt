package network


import android.media.Image
import models.MovieResult
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Url

interface MovieService {


    @GET
    fun getMovies(@Url url: String) : Call<MovieResult>
  //  fun getMovies() : Call<MovieResult>
    @GET
    fun getPoster(@Url url: String) : Call<Image>

}