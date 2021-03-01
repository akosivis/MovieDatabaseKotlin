package com.viselvis.moviedatabasekotlin.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.viselvis.moviedatabasekotlin.`interface`.ApiService
import com.viselvis.moviedatabasekotlin.adapters.MovieAdapter
import com.viselvis.moviedatabasekotlin.databinding.FragmentSearchBinding
import com.viselvis.moviedatabasekotlin.model.Movie
import com.viselvis.moviedatabasekotlin.model.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchFragment : Fragment() {
    private var retrofit: Retrofit? = null
    private lateinit var fragmentSearchBinding: FragmentSearchBinding
    private lateinit var inputString: String
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_search, container, false)
        fragmentSearchBinding = FragmentSearchBinding.inflate(inflater, container, false)
        return fragmentSearchBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize RecyclerView
        fragmentSearchBinding.rvMain.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        fragmentSearchBinding.rvMain.layoutManager = linearLayoutManager

        // set an onclick listener to the search button
        fragmentSearchBinding.mbtnSearch.setOnClickListener {
            if (fragmentSearchBinding.inputSearch.text.toString().isNotEmpty()) {
                inputString = fragmentSearchBinding.inputSearch.text.toString()
                searchQuery()
            } else {
                Toast.makeText(context, "Enter some text first!", Toast.LENGTH_SHORT).show()
            }
        }

        // set
    }


    private fun searchQuery() {
        val retrofitInstance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val service = retrofitInstance.create(ApiService::class.java)
        val call = service.searchMovies(API_KEY, inputString)
        call.enqueue(object: Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.code() == 200) {
                    Log.d(TAG, "responseBody.body() = " + response.body());

                    val listMovie: List<Movie> = response.body()?.results ?: emptyList()
                    // pass the list of movies to adapter
                    movieAdapter = MovieAdapter(listMovie)
                    fragmentSearchBinding.rvMain.adapter = movieAdapter
                } else {
                    Toast.makeText(context, "Please try again later!", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val API_KEY = "904014f69cce7fbc55530d98695e1e40"
    }
}