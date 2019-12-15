package com.example.xampletab.ui.main

import android.content.Context
import android.content.Intent

import android.os.Bundle

import android.view.*
import android.widget.FrameLayout
import androidx.appcompat.widget.SearchView

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.xampletab.R
import com.example.xampletab.ui.main.adapter.MovieCardAdapter
import com.example.xampletab.ui.movieDescription.MovieDescription
import com.example.xampletab.util.putExtraJson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.*
import models.Movie
import models.MovieResult

class MoviesListView : Fragment(), MoviesListContract.View{
    private val presenter = MoviesListPresenter()
    private val actionGenre:String = "28"
    private val comedyGenre:String = "35"
    private val romanceGenre:String = "10749"
    private val horrorGenre:String = "27"
    private var loading:FrameLayout? = null
    private var recyclerView:RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val root = inflater.inflate(R.layout.fragment_main, container, false)
        presenter.view = this
        loading = root.findViewById(R.id.loading)

        MOVIES_ATUAL = ArrayList<Movie>()

        PAGE_ATUAL = 1
        update(PAGE_ATUAL.toString())


        val menu:Menu? = activity?.toolBar?.menu
        val searchItem = menu?.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextChange(query: String?): Boolean {

                return true
            }
            override fun onQueryTextSubmit(query: String?): Boolean {
                val load:FrameLayout? = activity?.search_layout?.findViewById(R.id.loading)
                load?.visibility = View.VISIBLE
                presenter.getQueryMovies(query.toString(), "1")
                searchView.clearFocus()
                return true
            }
        })

        searchView?.addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewDetachedFromWindow(arg: View) {
                endSearch()
            }
            override fun onViewAttachedToWindow(arg0: View) {
                startSearch()
            }
        })



        return root
    }

    fun update(page: String){
        arguments?.get(GENRE).let {
            val genreOfThisFragment = when(it) {
                1 -> actionGenre
                2 -> comedyGenre
                3 -> romanceGenre
                4 -> horrorGenre
                else -> actionGenre
            }
            presenter.getMovies(genreOfThisFragment,page)
        }
    }



    fun startSearch(){
        activity?.tabs?.visibility = View.GONE
        activity?.view_pager?.visibility = View.GONE
        activity?.search_layout?.visibility = View.VISIBLE
    }

    fun endSearch(): Boolean{
        activity?.tabs?.visibility = View.VISIBLE
        activity?.view_pager?.visibility = View.VISIBLE
        activity?.search_layout?.visibility = View.GONE
        return true
    }

    companion object {
        private const val GENRE = "genre"
        private var PAGE_ATUAL = 1
        private lateinit var MOVIES_ATUAL:ArrayList<Movie>
        private var cantUPdate = false
        @JvmStatic
        fun newInstance(sectionNumber: Int): MoviesListView {
            return MoviesListView().apply {
                arguments = Bundle().apply {
                    putInt(GENRE, sectionNumber)
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
        MOVIES_ATUAL.addAll(movie.results)
        recyclerView = movieList

        recyclerView?.let{
            it.adapter = createAdapter(MOVIES_ATUAL, context)
            val layoutManager = StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL)
            recyclerView?.layoutManager = layoutManager
        }

        recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && !cantUPdate) {
                    cantUPdate = true
                    PAGE_ATUAL++
                    update((PAGE_ATUAL).toString() +  "")
                }

            }
        })

        cantUPdate = false

    }

    override fun querryItens(movie:MovieResult) {
        MOVIES_ATUAL.removeAll(MOVIES_ATUAL)
        MOVIES_ATUAL.addAll(movie.results)

        recyclerView = activity?.search_layout?.findViewById(R.id.movieList)
        recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && !cantUPdate) {
                    cantUPdate = true
                    PAGE_ATUAL++
                    update((PAGE_ATUAL).toString() +  "")
                }

            }
        })
        recyclerView?.let{
            it.adapter = createAdapter(MOVIES_ATUAL, context)
            val layoutManager = StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL)
            recyclerView?.layoutManager = layoutManager
        }
        val load:FrameLayout? = activity?.search_layout?.findViewById(R.id.loading)
        load?.visibility = View.GONE
        cantUPdate = false
    }


    private fun createAdapter(movies: ArrayList<Movie>, con:Context?): MovieCardAdapter {
        return MovieCardAdapter (movies,con){
                view: View, movie: Movie ->
              val intent = Intent (requireContext(), MovieDescription::class.java)
            intent.putExtraJson(movie)
            activity?.startActivity(intent)
        }
    }

}


