package com.aramsheroyan.worldcountries.app.utils

import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.ahmadrosid.svgloader.SvgLoader
import com.aramsheroyan.worldcountries.R

class ImageUtils {

    companion object {
        fun loadImage(
            fragment: Fragment,
            flag: String,
            view: ImageView
        ) {
            SvgLoader.pluck().with(fragment.activity)
                .setPlaceHolder(R.drawable.flag_placeholder, R.drawable.flag_placeholder)
                .load(flag, view)
        }
    }
}