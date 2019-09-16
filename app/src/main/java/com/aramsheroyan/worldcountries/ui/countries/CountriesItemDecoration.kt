/*
 * Created by Aram Sheroyan
 * https://github.com/aramsheroyan
 */

package com.aramsheroyan.worldcountries.ui.countries

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.aramsheroyan.worldcountries.R
import com.aramsheroyan.worldcountries.app.utils.UIUtils

class CountriesItemDecoration(val context: Context) : RecyclerView.ItemDecoration() {

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)

        val childCount = parent.childCount
        if (childCount != 1) {
            val paint = Paint()
            paint.style = Paint.Style.FILL
            paint.color = ContextCompat.getColor(context, R.color.light_gray)

            val margin = UIUtils.dpToPixels(context,24f).toFloat()
            for (i in 0 until childCount - 1) {
                val view = parent.getChildAt(i)
                c.drawLine(margin, view.bottom.toFloat(),parent.width-margin,view.bottom.toFloat(),paint)
            }
        }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        with(outRect) {
//            top = if (parent.getChildAdapterPosition(view) == 0) 0 else UiUtils.dpToPixels(context, CARD_TOP_MARGIN)
//            bottom = UiUtils.dpToPixels(context, CARD_BOTTOM_MARGIN)
        }
    }
}