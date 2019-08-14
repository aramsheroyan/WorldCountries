package com.example.worldcountries.ui.countryinfo


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.worldcountries.R
import com.example.worldcountries.app.WCApplication
import com.example.worldcountries.app.utils.ImageUtils
import com.example.worldcountries.data.room.Country
import kotlinx.android.synthetic.main.fragment_country_info.*
import timber.log.Timber
import java.util.Collections.replaceAll
import javax.inject.Inject

private const val NAME = "name"

class CountryInfoFragment : Fragment(), CountryInfoPresentationContract.View {

    private var name: String? = null
    private var component: CountryInfoComponent? = null
    @Inject
    lateinit var presenter: CountryInfoPresentationContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createComponent()?.inject(this)
        arguments?.let {
            name = it.getString(NAME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (name != null) {
            presenter.onGetCountryDetails(name!!)
        }
    }

    override fun showCountryDetails(country: Country) {
        Timber.d(country.toString())
        if (country.flag != null) {
            ImageUtils.loadImage(this, country.flag, flagImageView)
        }

        nameItemView.setStrings("Country", country.name)
        capitalItemView.setStrings("Capital", country.capital)
        regionItemView.setStrings("Region", country.region)
        subRegionItemView.setStrings("Subregion", country.subregion)
        populationItemView.setStrings("Population", country.population.toString())
        areaItemView.setStrings("Area", country.area.toString() + " km\u00B2")
        timeZonesItemView.setStrings(
            "Timezones", country.timezones.toString()
                .replace("[", "").replace("]", "")
        )
        currencyItemView.setStrings(
            "Currency", country.currencies.toString()
                .replace("[", "").replace("]", "")
        )

        languageItemView.setStrings(
            "Languages", country.languages.toString()
                .replace("[", "").replace("]", "")
        )


        openMapButton.setOnClickListener {
            val gmmIntentUri = Uri.parse("geo:0,0?q=${country.name}")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }

    }

    private fun createComponent(): CountryInfoComponent? {
        component = (activity?.application as WCApplication)
            .component
            .createSubComponent(CountryInfoModule(this))
        return component
    }
}
