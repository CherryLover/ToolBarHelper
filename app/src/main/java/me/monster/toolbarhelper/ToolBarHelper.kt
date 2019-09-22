package me.monster.toolbarhelper

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import me.monster.toolbarhelper.tools.ToolClickListener
import me.monster.toolbarhelper.tools.ToolViewActions
import me.monster.toolbarhelper.tools.toPix
import me.monster.toolbarhelper.view.ToolView

/**
 * @description
 * @author: Created jiangjiwei in 2019-09-20 14:53
 */
class ToolBarHelper(rootView: View, closeToolView: View, private var initTitle: String = ""): ToolViewActions {

    private val toolView = ToolView(rootView.context)

    init {
        addToolBar(rootView, closeToolView)
    }

    private fun addToolBar(root: View, closeToolView: View) {
        val layoutParams = ConstraintLayout.LayoutParams(root.layoutParams)
        layoutParams.width = ConstraintLayout.LayoutParams.MATCH_PARENT
        layoutParams.height = 48.toPix()
        toolView.layoutParams = layoutParams
        toolView.id = View.generateViewId()
        if (initTitle.isNotEmpty()) {
            toolView.setTitle(initTitle)
        }
        if (root is ConstraintLayout) {
            val relation = ConstraintSet()
            checkId(root)
            root.addView(toolView)
            relation.clone(root)
            relation.connect(toolView.id, ConstraintSet.START, root.id, ConstraintSet.START)
            relation.connect(toolView.id, ConstraintSet.END, root.id, ConstraintSet.END)
            relation.connect(toolView.id, ConstraintSet.TOP, root.id, ConstraintSet.TOP)
            relation.applyTo(root)

            val closeRelation = ConstraintSet()
            closeRelation.clone(root)
            closeRelation.connect(
                closeToolView.id,
                ConstraintSet.TOP,
                toolView.id,
                ConstraintSet.BOTTOM
            )
            closeRelation.applyTo(root)
        }
    }

    fun setListener(listener: ToolClickListener) {
        toolView.listener = listener
    }

    override fun setMenu(menu: String) {
        toolView.setMenu(menu)
    }

    override fun setMenuImg(id: Int) {
        toolView.setMenuImg(id)
    }

    override fun setTitle(title: String) {
        toolView.setTitle(title)
    }

    private fun checkId(root: ViewGroup) {
        Log.e("BaseFragment ", "status id rootView Id ${root.id}")
        if (root.id == -1) {
            root.id = View.generateViewId()
        }
        for (i in 0 until root.childCount) {
            val childAt = root.getChildAt(i)
            if (childAt.id == -1) {
                childAt.id = View.generateViewId()
            }
            if (childAt is ViewGroup) {
                checkId(childAt)
            }
        }
    }
}