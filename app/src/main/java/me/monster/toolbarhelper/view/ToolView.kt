package me.monster.toolbarhelper.view

import android.content.Context
import android.view.View
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.tool_view.view.*
import me.monster.toolbarhelper.R
import me.monster.toolbarhelper.nav.NavProvider
import me.monster.toolbarhelper.tools.ToolClickListener
import me.monster.toolbarhelper.tools.ToolViewActions
import me.monster.toolbarhelper.tools.gone
import me.monster.toolbarhelper.tools.visible

/**
 * @description
 * @author: Created jiangjiwei in 2019-09-19 16:51
 */
class ToolView(context: Context) : ConstraintLayout(context), ToolViewActions {
    var listener: ToolClickListener? = null
    val vFakeStatus: View
    var navInterceptor: Boolean = false
    var navProvider: NavProvider? = null
        set(value: NavProvider?){
            navInterceptor = value != null
            field = value
        }

    init {
        View.inflate(context, R.layout.tool_view, this)
        vFakeStatus = findViewById(R.id.v_tool_status_fake)

        iv_tool_back.setOnClickListener { navClick() }
        tv_tool_title.setOnClickListener { listener?.onClick(title) }
        tv_tool_menu.setOnClickListener { listener?.onClick(menu) }
        img_tool_menu.setOnClickListener { listener?.onClick(menuImg) }
    }

    private fun navClick() {
        if (navInterceptor) {
            navProvider?.navigation(iv_tool_back)
        } else {
            listener?.onClick(navigation)
        }
    }

    override fun setTitle(title: String) {
        tv_tool_title.text = title
    }

    override fun setMenu(menu: String) {
        tv_tool_menu.visible()
        img_tool_menu.gone()
        tv_tool_menu.text = menu
    }

    override fun setMenuImg(@DrawableRes id: Int) {
        img_tool_menu.visible()
        tv_tool_menu.gone()
        img_tool_menu.setImageResource(id)
    }

    companion object {
        const val navigation = 1
        const val title = 2
        const val menu = 3
        const val menuImg = 4
    }
}
