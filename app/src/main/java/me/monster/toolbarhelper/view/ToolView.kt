package me.monster.toolbarhelper.view

import android.content.Context
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.tool_view.view.*
import me.monster.toolbarhelper.R

/**
 * @description
 * @author: Created jiangjiwei in 2019-09-19 16:51
 */
class ToolView(context: Context) : ConstraintLayout(context) {
    init {
        View.inflate(context, R.layout.tool_view, this)

        iv_tool_back.setOnClickListener {
            val findNavController = Navigation.findNavController(it)
            val navigateUp = findNavController.navigateUp()
            if (!navigateUp) {
                // TODO: 2019-09-19 把点击事件抛出去
            }
        }
    }

    fun setTitle(title: String) {
        tv_tool_title.text = title
    }
}
