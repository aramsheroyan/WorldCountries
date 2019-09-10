package com.aramsheroyan.worldcountries.ui.countries

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.aramsheroyan.worldcountries.data.room.Country
import kotlinx.android.synthetic.main.country_view_holder.view.*

class CountryViewHolder : RecyclerView.ViewHolder {

    private var listener: OnCountrySelectedListener

    interface OnCountrySelectedListener {
        fun onSelected(countryName: String?)
    }

    constructor(itemView: View, listener: OnCountrySelectedListener) : super(itemView) {
        this.listener = listener
    }

    fun populateData(country: Country) {
        val name = country.name
        itemView.nameTextView.text = name
        itemView.frameLayout.setOnClickListener {
            listener.onSelected(name)
        }
    }
}