/*
 * Created by Aram Sheroyan
 * https://github.com/aramsheroyan
 */

package com.aramsheroyan.worldcountries.ui.countryinfo

import com.aramsheroyan.worldcountries.BaseTest
import com.aramsheroyan.worldcountries.data.countries.CountriesDataContract
import com.aramsheroyan.worldcountries.data.room.Country
import io.reactivex.Maybe
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class CountryInfoPresenterTest : BaseTest() {

    @Mock
    private lateinit var view: CountryInfoPresentationContract.View
    @Mock
    private lateinit var repository: CountriesDataContract.Repository

    private lateinit var presenter: CountryInfoPresentationContract.Presenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = CountryInfoPresenter(view, repository)
    }

    @Test
    fun onGetCountryDetails_Successful() {
        // 1. (Given) Set up conditions required for the test
        val name = "name"
        val country = Country(
            0,
            "",
            "",
            "",
            "",
            "",
            "",
            0,
            0f,
            listOf(),
            listOf(),
            listOf(),
            listOf(),
            ""
        )
        `when`(repository.getCountryByName(name)).thenReturn(Maybe.just(country))

        // 2. (When) Then perform one or more actions
        presenter.onGetCountryDetails(name)

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(view).showCountryDetails(country)
    }
}