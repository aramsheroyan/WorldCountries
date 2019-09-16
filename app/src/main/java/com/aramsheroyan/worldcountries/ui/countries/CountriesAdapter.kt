/*
 * Created by Aram Sheroyan
 * https://github.com/aramsheroyan
 */

package com.aramsheroyan.worldcountries.ui.countries

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.aramsheroyan.worldcountries.R
import com.aramsheroyan.worldcountries.data.room.Country
import android.widget.Filter


class CountriesAdapter(
    private var countriesList: List<Country>,
    private val listener: CountryViewHolder.OnCountrySelectedListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    private var countries = mutableListOf<Country>()
    private var countriesFull = mutableListOf<Country>()

    init {
        countries = countriesList.toMutableList()
        countriesFull = countriesList.toMutableList()

    }

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

    override fun getFilter(): Filter {
        return countryFilter
    }

    private val countryFilter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList = mutableListOf<Country>()

            if (constraint == null || constraint.length == 0) {
                filteredList.addAll(countriesFull)
            } else {
                val filterPattern = constraint.toString().toLowerCase().trim { it <= ' ' }

                for (item in countriesFull) {
                    if (item.name!!.toLowerCase().contains(filterPattern)) {
                        filteredList.add(item)
                    }
                }
            }

            val results = FilterResults()
            results.values = filteredList

            return results
        }

        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            countries.clear()
            countries.addAll(results.values as List<Country>)
            notifyDataSetChanged()
        }
    }
}