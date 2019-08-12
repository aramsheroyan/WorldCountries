package com.example.worldcountries.app.utils

import android.content.Context

class UIUtils {
    companion object {
        fun dpToPixels(context: Context, dp: Float): Int {
            val scale = context.resources.displayMetrics.density
            return (dp * scale + 0.5f).toInt()
        }
    }
}