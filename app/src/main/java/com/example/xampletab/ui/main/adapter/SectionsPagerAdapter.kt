package com.example.xampletab.ui.main.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.xampletab.R
import com.example.xampletab.ui.main.MoviesListView

private val TAB_TITLES = arrayOf(
    R.string.tab_acao_title,
    R.string.tab_comedia_title,
    R.string.tab_romance_title,
    R.string.tab_terror_title
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return MoviesListView.newInstance(
            position +1
        )
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return 4
    }
}