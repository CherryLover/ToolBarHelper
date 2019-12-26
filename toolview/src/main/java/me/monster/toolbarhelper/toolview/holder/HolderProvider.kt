package me.monster.toolbarhelper.toolview.holder

import android.content.Context
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Placeholder

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
        phRt.setContentId(addView(type))
    }

    /**
     * 实现该方法完成对不同类型的 View 的创建任务
     */
    abstract fun createView(context: Context, type: Int): View


}