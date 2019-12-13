package network

import androidx.annotation.Nullable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitClientInstance {
    // key 3 bfc1e896f4a608c6bf45c5c8398cc9f5
    // key 4 eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiZmMxZTg5NmY0YTYwOGM2YmY0NWM1YzgzOThjYzlmNSIsInN1YiI6IjVkZWZkNWRjMDI1NzY0MDAxNjU3M2QzOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.W0gCKvwKglrHvAzi8vcR-qNQmpFP6lIKjdk7PwxgZv4

    private val BASE_URL:String = "https://api.themoviedb.org/3/"
    private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    fun movieService() : MovieService{
        return retrofit.create(MovieService::class.java)
    }

}