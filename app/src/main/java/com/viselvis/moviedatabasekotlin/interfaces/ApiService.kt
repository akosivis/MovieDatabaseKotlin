package com.viselvis.moviedatabasekotlin.interfaces

import com.viselvis.moviedatabasekotlin.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search/movie")
    fun searchMovies(@Query("api_key") apiKey: String, @Query("query") query: String): Call<MovieResponse>
    // fun searchMovies(): Call<Movie>
}