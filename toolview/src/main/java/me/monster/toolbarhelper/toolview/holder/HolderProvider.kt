package me.monster.toolbarhelper.toolview.holder

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Placeholder
import me.monster.toolbarhelper.toolview.R
import me.monster.toolbarhelper.toolview.tools.inVisible

/**
 * @description
 * @author: Created jiangjiwei in 2019-12-25 11:54
 */
abstract class HolderProvider(
    var vEmpty: View,
    var clRoot: ConstraintLayout,
    var phRt: Placeholder
) {

    fun hide() {
        var removeViewAt = -1
        for (i in 0..clRoot.childCount) {
            if (phRt.content == null) {
                continue
            }
            if (clRoot.getChildAt(i).id == phRt.content.id) {
                removeViewAt = i
                break
            }
        }
        phRt.setContentId(vEmpty.id)
        if (removeViewAt != -1) {
            clRoot.removeViewAt(removeViewAt)
        }
    }

    private fun addView(type: Int): Int {
        val view = createView(clRoot.context, type)
        if (view.id == -1) {
            view.id = View.generateViewId()
        }
        clRoot.addView(view)
        return view.id
    }

    fun showView(type: Int) {
        hideMenu()
        phRt.setContentId(addView(type))
    }

    fun hideMenu() {
        clRoot.findViewById<ImageView>(R.id.img_tool_menu)?.inVisible()
        clRoot.findViewById<TextView>(R.id.tv_tool_menu)?.inVisible()
    }

    /**
     * 实现该方法完成对不同类型的 View 的创建任务
     */
    abstract fun createView(context: Context, type: Int): View


}