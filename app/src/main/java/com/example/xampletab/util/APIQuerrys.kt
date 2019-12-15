package com.example.cubosmovies.Home.util


class APIQuerrys {
    private val BASE_URL:String = "https://api.themoviedb.org/3/"
    private val BASE_IMG_URL:String = "https://image.tmdb.org/t/p/"
    private val DISCOVERY_FUNCTION = "discover/"
    private val SEARCH_FUNCTION = "search/"
    private val API_KEY_v3 = "api_key=bfc1e896f4a608c6bf45c5c8398cc9f5"
    private val LANGUAGE:String = "pt-BR"

     fun getMoviesByGenre(genreID: String, page:String) : String{
        return BASE_URL + DISCOVERY_FUNCTION + "movie?" + API_KEY_v3 + "&language="+LANGUAGE+"&sort_by=popularity.desc&include_video=false&page="+page+"&with_genres=" + genreID
    }

    fun getPoster(poster_path:String, size:String): String{

        return BASE_IMG_URL + size +poster_path

    }

    fun getBackDrop(back_dropPath:String?, size:String): String{
        return BASE_IMG_URL + size +back_dropPath
    }

    fun getQuerryedMovies(querry: String, page:String) : String{
        return BASE_URL + SEARCH_FUNCTION + "movie?" + API_KEY_v3 + "&language="+LANGUAGE+"&sort_by=popularity.desc&include_video=false&page="+page+"&query="+querry
    }
}