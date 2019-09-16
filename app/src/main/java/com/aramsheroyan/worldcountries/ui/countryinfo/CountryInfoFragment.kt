/*
 * Created by Aram Sheroyan
 * https://github.com/aramsheroyan
 */

package com.aramsheroyan.worldcountries.ui.countryinfo


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

import com.aramsheroyan.worldcountries.R
import com.aramsheroyan.worldcountries.app.WCApplication
import com.aramsheroyan.worldcountries.app.utils.ImageUtils
import com.aramsheroyan.worldcountries.data.room.Country
import kotlinx.android.synthetic.main.fragment_country_info.*
import timber.log.Timber
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
        (activity as AppCompatActivity).supportActionBar?.show()
        if (name != null) {
            presenter.onGetCountryDetails(name!!)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroyed()
    }

    override fun showCountryDetails(country: Country) {
        Timber.d(country.toString())
        if (country.flag != null) {
            ImageUtils.loadImage(this, country.flag, flagImageView)
        }

        nameItemView.setStrings(getString(R.string.country), country.name)
        capitalItemView.setStrings(getString(R.string.capital), country.capital)
        regionItemView.setStrings(getString(R.string.region), country.region)
        subRegionItemView.setStrings(getString(R.string.subregion), country.subregion)
        populationItemView.setStrings(getString(R.string.population), country.population.toString())
        areaItemView.setStrings(getString(R.string.area), country.area.toString() + " km\u00B2")
        timeZonesItemView.setStrings(
            getString(R.string.timezones), stripBrackets(country.timezones.toString())

        )
        currencyItemView.setStrings(
            getString(R.string.currency), stripBrackets(country.currencies.toString())
        )

        languageItemView.setStrings(
            getString(R.string.languages), stripBrackets(country.languages.toString())
        )


        openMapButton.setOnClickListener {
            val gmmIntentUri = Uri.parse("geo:0,0?q=${country.name}")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }

    }

    private fun stripBrackets(text: String): String {
        return text.replace("[", "").replace("]", "")
    }

    private fun createComponent(): CountryInfoComponent? {
        component = (activity?.application as WCApplication)
            .component
            .createSubComponent(CountryInfoModule(this))
        return component
    }
}
