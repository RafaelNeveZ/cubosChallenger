package com.example.xampletab.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.xampletab.R
import com.example.xampletab.ui.main.adapter.MovieCardAdapter
import kotlinx.android.synthetic.main.fragment_main.*
import models.MovieResult

class MoviesFragment : Fragment(), PageViewContract.View {
    private val presenter = PageViewPresenter()
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
        fun newInstance(sectionNumber: Int): MoviesFragment {
            return MoviesFragment().apply {
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

    override fun showError(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun bindMovieList(movie: MovieResult) {
        val recyclerView = movieList
        recyclerView.adapter =
            MovieCardAdapter(
                movie.results,
                requireContext()
            )
        val layoutManager = StaggeredGridLayoutManager(
            2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
    }
}