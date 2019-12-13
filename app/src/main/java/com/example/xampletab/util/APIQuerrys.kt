package com.example.cubosmovies.Home.util

import android.net.Uri
import android.util.Log

class APIQuerrys {
    private val BASE_URL:String = "https://api.themoviedb.org/3/"
    private val BASE_IMG_URL:String = "https://image.tmdb.org/t/p/"
    private val DISCOVERY_FUNCTION = "discover/"
    private val API_KEY_v3 = "api_key=bfc1e896f4a608c6bf45c5c8398cc9f5"
    private val API_KEY_v4 = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiZmMxZTg5NmY0YTYwOGM2YmY0NWM1YzgzOThjYzlmNSIsInN1YiI6IjVkZWZkNWRjMDI1NzY0MDAxNjU3M2QzOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.W0gCKvwKglrHvAzi8vcR-qNQmpFP6lIKjdk7PwxgZv4"
    private val LANGUAGE:String = "pt-BR"
    private val PAGE:String = "2"

     fun getMoviesByGenre(genreID: String) : String{
        return BASE_URL + DISCOVERY_FUNCTION + "movie?" + API_KEY_v3 + "&language="+LANGUAGE+"&sort_by=popularity.desc&include_video=false&page="+PAGE+"&with_genres=" + genreID
    }

    fun getPoster(poster_path:String, size:String): String{

        return BASE_IMG_URL + size +poster_path

    }
}