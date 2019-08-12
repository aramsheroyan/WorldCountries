package com.example.worldcountries.ui.countries


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.worldcountries.R
import com.example.worldcountries.app.WCApplication
import com.example.worldcountries.data.room.Country
import kotlinx.android.synthetic.main.fragment_countries.*
import javax.inject.Inject


class CountriesFragment : Fragment(), CountriesPresentationContract.View {

    var countriesComponent: CountriesComponent? = null

    @Inject
    lateinit var presenter: CountriesPresentationContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createComponent()?.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_countries, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onScreenStarted()

    }

    override fun populateData(countries: List<Country>) {
        val countriesAdapter = CountriesAdapter(countries)
        if (countriesRecyclerView?.itemDecorationCount == 0)
            countriesRecyclerView.addItemDecoration(CountriesItemDecoration(context!!))
        countriesRecyclerView.adapter = countriesAdapter
        countriesRecyclerView.layoutManager =  LinearLayoutManager(context, RecyclerView.VERTICAL, false)

    }

    private fun createComponent(): CountriesComponent? {
        countriesComponent = (activity?.application as WCApplication)
            .component
            .createSubComponent(CountriesModule(this))
        return countriesComponent
    }


}
