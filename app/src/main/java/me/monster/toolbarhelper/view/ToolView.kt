package me.monster.toolbarhelper.view

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.tool_view.view.*
import me.monster.toolbarhelper.R
import me.monster.toolbarhelper.nav.PopProvider
import me.monster.toolbarhelper.tools.*

/**
 * @description
 * @author: Created jiangjiwei in 2019-09-19 16:51
 */
class ToolView(context: Context) : ConstraintLayout(context), ToolViewActions {
    var listener: ToolClickListener? = null

    private val vFakeStatus: View
    private val ivBack: ImageView
    private val tvTitle: TextView
    private val tvMenu: TextView
    private val ivMenuImg: ImageView

    private var navInterceptor: Boolean = false
    var popProvider: PopProvider? = null
        set(value){
            navInterceptor = value != null
            field = value
        }

    init {
        View.inflate(context, R.layout.tool_view, this)
        vFakeStatus = findViewById(R.id.v_tool_status_fake)
        ivBack = findViewById(R.id.iv_tool_back)
        tvTitle = findViewById(R.id.tv_tool_title)
        tvMenu = findViewById(R.id.tv_tool_menu)
        ivMenuImg = findViewById(R.id.img_tool_menu)

        ivBack.setOnClickListener { navClick() }
        tvTitle.setOnClickListener { listener?.onClick(title) }
        tvMenu.setOnClickListener { listener?.onClick(menu) }
        ivMenuImg.setOnClickListener { listener?.onClick(menuImg) }

        val fakeParams = vFakeStatus.layoutParams
        fakeParams.height = StatusBarUtils.getHeight(context)
        vFakeStatus.layoutParams = fakeParams
    }

    private fun navClick() {
        if (navInterceptor) {
            popProvider?.pop(ivBack)
        } else {
            listener?.onClick(navigation)
        }
    }

    override fun setTitle(title: String) {
        tvTitle.text = title
    }

    override fun setMenu(menu: String) {
        tvMenu.visible()
        ivMenuImg.gone()
        tvMenu.text = menu
    }

    override fun setMenuImg(@DrawableRes id: Int) {
        ivMenuImg.visible()
        tvMenu.gone()
        ivMenuImg.setImageResource(id)
    }

    companion object {
        const val navigation = 1
        const val title = 2
        const val menu = 3
        const val menuImg = 4
    }
}
