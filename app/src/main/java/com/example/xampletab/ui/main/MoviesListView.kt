package com.example.xampletab.ui.main

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.xampletab.R
import com.example.xampletab.ui.main.adapter.MovieCardAdapter
import com.example.xampletab.ui.movieDescription.MovieDescription
import com.example.xampletab.util.putExtraJson
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.movie_item.*
import models.Movie
import models.MovieResult

class MoviesListView : Fragment(), MoviesListContract.View {
    private val presenter = MoviesListPresenter()
    private val actionGenre:String = "28"
    private val comedyGenre:String = "35"
    private val romanceGenre:String = "10749"
    private val horrorGenre:String = "27"




    private var loading:FrameLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_main, container, false)
        loading = root.findViewById(R.id.loading)

        arguments?.get(ARG_SECTION_NUMBER).let {
            val genreOfThisFragment = when(it) {
                1 -> actionGenre
                2 -> comedyGenre
                3 -> romanceGenre
                4 -> horrorGenre
                else -> actionGenre
            }
            presenter.view = this
            presenter.getMovies(genreOfThisFragment)
        }
        return root
    }

    companion object {
        private const val ARG_SECTION_NUMBER = "genre"
        @JvmStatic
        fun newInstance(sectionNumber: Int): MoviesListView {
            return MoviesListView().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    override fun showLoadingDialog() {
       loading?.visibility = View.VISIBLE
    }

    override fun hideLoadingDialog() {
        loading?.visibility = View.GONE
    }

    override fun bindMovieList(movie: MovieResult) {
        val recyclerView = movieList
        recyclerView.adapter = createAdapter(movie.results)
        val layoutManager = StaggeredGridLayoutManager(
            2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
    }
    private fun createAdapter(movies: ArrayList<Movie>): MovieCardAdapter {
        return MovieCardAdapter (movies){
                view: View, movie: Movie ->

            Snackbar.make(view, movie.title, Snackbar.LENGTH_LONG).show()
              val intent = Intent (requireContext(), MovieDescription::class.java)

            intent.putExtraJson(movie)

            activity?.startActivity(intent)
        }
    }
}