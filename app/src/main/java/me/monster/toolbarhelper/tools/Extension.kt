package me.monster.toolbarhelper.tools

import android.content.res.Resources
import android.util.TypedValue

/**
 * @description
 * @author: Created jiangjiwei in 2019-09-20 15:11
 */
fun Int.toPix(): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()
}