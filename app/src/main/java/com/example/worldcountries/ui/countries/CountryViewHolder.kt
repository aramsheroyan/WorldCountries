package com.example.worldcountries.ui.countries

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.worldcountries.data.room.Country
import kotlinx.android.synthetic.main.country_view_holder.view.*

class CountryViewHolder: RecyclerView.ViewHolder {

    constructor(itemView: View): super(itemView){

    }

    fun populateData(country: Country){
        itemView.nameTextView.text = country.name
    }
}