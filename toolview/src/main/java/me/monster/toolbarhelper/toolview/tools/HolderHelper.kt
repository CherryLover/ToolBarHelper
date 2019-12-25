package me.monster.toolbarhelper.toolview.tools

import android.content.Context
import android.view.View
import android.widget.CheckBox
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Placeholder

/**
 * @description
 * @author: Created jiangjiwei in 2019-12-25 11:54
 */
object HolderHelper {

    // TODO: 2019-12-25 操作 Holder 的任务，通过 ToolBarHelper 进行，将 Holder 定义为非单例模式，持有 vEmpty、clRoot、所有 placeHolder 的引用。
    // TODO: 2019-12-25 通过接口 或 抽象类完成对 不同状态下 View 的获取；

    fun showCheck(empty: View, phHolder: Placeholder, clRoot: ConstraintLayout) {
        if (phHolder.content != null) {
            L.d("Content is not null let me hide it")
            hide(empty, phHolder, clRoot)
        }
        phHolder.setContentId(addView(clRoot, checkAll))
    }

    fun hide(empty: View,phHolder: Placeholder, clRoot: ConstraintLayout) {
        var removeViewAt = -1
        for (i in 0..clRoot.childCount) {
            if (clRoot.getChildAt(i).id == phHolder.content.id) {
                removeViewAt = i
                break
            }
        }
        phHolder.setContentId(empty.id)
        if (removeViewAt != -1) {
            clRoot.removeViewAt(removeViewAt)
        }
    }

    /**
     * 添加一个 View
     */
    private fun addView(clRoot: ConstraintLayout, type: Int): Int {
        val view = getView(clRoot.context, type)
        view.id = View.generateViewId()
        clRoot.addView(view)
        return view.id
    }

    private fun getView(context: Context, type: Int): View {
        return when (type) {
            checkAll -> CheckBox(context)
            empty -> View(context)
            else -> View(context)
        }
    }

    const val empty = 1
    const val checkAll = 2


}