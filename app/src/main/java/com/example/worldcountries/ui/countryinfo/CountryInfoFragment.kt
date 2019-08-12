package com.example.worldcountries.ui.countryinfo


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.worldcountries.R
import com.example.worldcountries.app.WCApplication
import com.example.worldcountries.data.room.Country
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
        if (name != null) {
            presenter.onGetCountryDetails(name!!)
        }
    }

    override fun showCountryDetails(country: Country) {
        Timber.d(country.toString())
    }

    private fun createComponent(): CountryInfoComponent? {
        component = (activity?.application as WCApplication)
            .component
            .createSubComponent(CountryInfoModule(this))
        return component
    }
}
