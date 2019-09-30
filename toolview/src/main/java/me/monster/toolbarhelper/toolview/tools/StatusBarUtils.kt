package me.monster.toolbarhelper.toolview.tools

import android.content.Context

/**
 * @description
 * @author: Created jiangjiwei in 2019-09-20 15:11
 */
object StatusBarUtils {

    fun getHeight(context: Context): Int {
        var statusBarHeight = 0
        try {
            val resourceId =
                context.resources.getIdentifier("status_bar_height", "dimen", "android")
            if (resourceId > 0) {
                statusBarHeight = context.resources.getDimensionPixelSize(resourceId)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return statusBarHeight
    }

}