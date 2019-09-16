/*
 * Created by Aram Sheroyan
 * https://github.com/aramsheroyan
 */

package com.aramsheroyan.worldcountries.ui.countryinfo

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.cardview.widget.CardView
import com.aramsheroyan.worldcountries.R
import kotlinx.android.synthetic.main.country_info_item_view.view.*

/**
 * TODO: document your custom view class.
 */
class CountryInfoItemView : CardView {

    var title: String? = null
    var value: String? = null

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        // Load attributes
        val attributeArray = context.obtainStyledAttributes(
            attrs, R.styleable.CountryInfoItemView, defStyle, 0
        )

        try {
            title = attributeArray.getString(R.styleable.CountryInfoItemView_title)
            value = attributeArray.getString(R.styleable.CountryInfoItemView_value)
        } finally {
            attributeArray.recycle()
        }

        View.inflate(context, R.layout.country_info_item_view, this)

        titleTextView.text = title
        valueTextView.text = value

    }

    fun setStrings(title: String?, value: String?) {
        titleTextView.text = title
        valueTextView.text = value
    }
}
