package me.monster.toolbarhelper.toolview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import me.monster.toolbarhelper.toolview.nav.PopProvider
import me.monster.toolbarhelper.toolview.tools.ToolClickListener
import me.monster.toolbarhelper.toolview.tools.ToolViewActions
import me.monster.toolbarhelper.toolview.tools.sp

/**
 * @description
 * @author: Created jiangjiwei in 2019-10-25 14:47
 */
open class BaseToolView(context: Context, attributeSet: AttributeSet? = null) : FrameLayout(context, attributeSet), ToolViewActions {

    var listener: ToolClickListener? = null
    var navInterceptor: Boolean = false

    var popProvider: PopProvider? = null
        set(value) {
            navInterceptor = value != null
            field = value
        }

    fun navClick(navView: View) {
        if (navInterceptor) {
            popProvider?.pop(navView)
        } else {
            listener?.onClick(navigation)
        }
    }

    override fun setTitle(title: String) {
    }

    override fun setMenu(menu: String) {
    }

    override fun setMenuImg(id: Int) {
    }

    override fun setBgColor(id: Int) {
    }

    companion object {
        const val notFound = -1
        val defaultTitleTextSize = 20.sp()
        val defaultMenuTextSize = 15.sp()

        const val navigation = 1
        const val title = 2
        const val menu = 3
        const val menuImg = 4

        const val nav_gone = View.GONE
        const val nav_visible = View.VISIBLE
        const val nav_invisible = View.INVISIBLE
    }

}