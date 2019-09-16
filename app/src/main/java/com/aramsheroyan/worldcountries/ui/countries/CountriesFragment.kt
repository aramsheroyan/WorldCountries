/*
 * Created by Aram Sheroyan
 * https://github.com/aramsheroyan
 */

package com.aramsheroyan.worldcountries.ui.countries


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.aramsheroyan.worldcountries.R
import com.aramsheroyan.worldcountries.app.WCApplication
import com.aramsheroyan.worldcountries.data.room.Country
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.fragment_countries.*
import javax.inject.Inject


class CountriesFragment : Fragment(), CountriesPresentationContract.View,
    CountryViewHolder.OnCountrySelectedListener {

    private var countriesComponent: CountriesComponent? = null

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
        (activity as AppCompatActivity).supportActionBar?.hide()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_countries, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onScreenStarted()
        setButton(allButton, true)
        allButton.setOnClickListener {
            presenter.onScreenStarted()
            setButton(allButton, true)
            setButton(learnedButton, false)


        }

        learnedButton.setOnClickListener {
            presenter.onLearnedCountriesSelected()
            setButton(allButton, false)
            setButton(learnedButton, true)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroyed()
    }

    override fun populateData(countries: List<Country>) {
        val countriesAdapter = CountriesAdapter(countries, this)
        if (countriesRecyclerView?.itemDecorationCount == 0)
            countriesRecyclerView.addItemDecoration(CountriesItemDecoration(context!!))

        countriesRecyclerView.adapter = countriesAdapter
        countriesRecyclerView.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        loadingAnimation.visibility = View.GONE
        countriesRecyclerView.visibility = View.VISIBLE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String?): Boolean {
                countriesAdapter.filter.filter(newText);
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
        })

    }

    override fun onSelected(countryName: String?) {

        view?.findNavController()
            ?.navigate(
                R.id.action_navigation_countries_to_countryInfoFragment,
                bundleOf("name" to countryName)
            )
    }

    private fun setButton(button: MaterialButton, checked: Boolean) {
        var background = R.color.white
        var textColor = R.color.colorPrimary
        if (checked) {
            background = R.color.colorPrimary
            textColor = R.color.white
        }
        button.backgroundTintList = ContextCompat.getColorStateList(
            context!!,
            background
        )

        button.setTextColor(
            ContextCompat.getColorStateList(
                context!!,
                textColor
            )
        )
    }

    private fun createComponent(): CountriesComponent? {
        countriesComponent = (activity?.application as WCApplication)
            .component
            .createSubComponent(CountriesModule(this))
        return countriesComponent
    }


}
