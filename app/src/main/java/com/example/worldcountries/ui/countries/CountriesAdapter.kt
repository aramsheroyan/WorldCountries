package com.example.worldcountries.ui.countries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.worldcountries.R
import com.example.worldcountries.data.room.Country

class CountriesAdapter(
    private val countries: List<Country>,
    private val listener: CountryViewHolder.OnCountrySelectedListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return countries.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CountryViewHolder) {
            holder.populateData(countries[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.country_view_holder, parent, false)
        return CountryViewHolder(view, listener)
    }
}