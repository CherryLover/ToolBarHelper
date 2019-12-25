package me.monster.toolbarhelper.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_singl_view.*
import me.monster.toolbarhelper.R
import me.monster.toolbarhelper.base.BaseFragment
import me.monster.toolbarhelper.toolview.ToolView
import me.monster.toolbarhelper.toolview.tools.ToolClickListener
import me.monster.toolbarhelper.toolview.tools.sp

/**
 * @description
 * @author: Created jiangjiwei in 2019-09-27 17:03
 */
class SingleViewFragment : BaseFragment(), ToolClickListener {
    override fun onClick(what: Int) {
        if (what == ToolView.menuImg) {
            tool_single_tool.navIconVisible = View.VISIBLE
            tool_single_tool.menuImgVisible = View.GONE
            tool_single_tool.menuTextVisible = View.VISIBLE
        } else if (what == ToolView.menu) {
            tool_single_tool.navIcon = R.drawable.ic_arrow_back_black_24dp
            tool_single_tool.menuTextVisible = View.GONE
        }

    }

    override fun init() {
    }

    override fun getTitle(): String {
        return ""
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_singl_view
    }

    override fun closeToolView(rootView: View): View? {
        return null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tool_single_tool.listener = this

        btn_single_setMenuColor.setOnClickListener {
            tool_single_tool.menuTextColor = Color.RED
        }

        btn_single_setMenuSize.setOnClickListener {
            tool_single_tool.menuTextSize = 22.sp()
        }

        btn_single_setTitleColor.setOnClickListener {
            tool_single_tool.titleTextColor = Color.RED
        }

        btn_single_setTitleSize.setOnClickListener {
            tool_single_tool.titleTextSize = 16.sp()
        }

        btn_single_reset.setOnClickListener {
            tool_single_tool.titleTextSize = ToolView.defaultTitleTextSize
            tool_single_tool.menuTextSize = ToolView.defaultMenuTextSize
            tool_single_tool.titleTextColor = Color.WHITE
            tool_single_tool.menuTextColor = Color.BLACK
        }


    }
}