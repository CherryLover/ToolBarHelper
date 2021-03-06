package me.monster.toolbarhelper.toolview.tools

import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import me.monster.toolbarhelper.toolview.ToolView
import me.monster.toolbarhelper.toolview.holder.DefaultHolder
import me.monster.toolbarhelper.toolview.holder.HolderProvider
import me.monster.toolbarhelper.toolview.nav.PopProvider

/**
 * @description
 * @author: Created jiangjiwei in 2019-09-20 14:53
 */
class ToolBarHelper(
    rootView: View,
    closeToolView: View,
    private var toolView: ToolView = ToolView(rootView.context),
    private var initTitle: String = ""
) : ToolViewActions {

    constructor(
        rootView: View,
        closeToolView: View,
        initTitle: String = ""
    ) : this(rootView, closeToolView, ToolView(rootView.context), initTitle)

    val holderProvider: HolderProvider

    init {
        addToolBar(rootView, closeToolView)
        holderProvider = toolView.generateHolderProvider()
    }

    private fun addToolBar(root: View, closeToolView: View) {
        val layoutParams = ConstraintLayout.LayoutParams(root.layoutParams)
        layoutParams.width = ConstraintLayout.LayoutParams.MATCH_PARENT
        layoutParams.height = ConstraintLayout.LayoutParams.WRAP_CONTENT
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

    fun setPopProvider(popProvider: PopProvider?) {
        toolView.popProvider = popProvider
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

    override fun setBgColor(@ColorRes id: Int) {
        toolView.setBgColor(id)
    }

    fun setBackgroundColor(@ColorInt color: Int) {
        toolView.setBackgroundColor(color)
    }

    fun setBackgroundRes(@DrawableRes id: Int) {
        toolView.setBackgroundResource(id)
    }

    fun setBackground(backgroundDrawable: Drawable) {
        toolView.background = backgroundDrawable
    }

    /**
     * this array contains vEmpty clRoot phRight。Use them can build a HolderProvider
     */
    fun getHolderProviderRequires(): Array<View> {
        return toolView.getHolderProviderRequires()
    }

    fun getDefaultHolderProvider(): DefaultHolder {
        return if (holderProvider is DefaultHolder) {
            holderProvider
        } else {
            toolView.generateHolderProvider() as DefaultHolder
        }
    }

    private fun checkId(root: ViewGroup) {
        L.d("status id rootView Id ${root.id}")
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