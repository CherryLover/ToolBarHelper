package me.monster.toolbarhelper.toolview.holder

import android.content.Context
import android.view.View
import android.widget.CheckBox
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Placeholder

/**
 * @description
 * @author: Created jiangjiwei in 2019-12-26 10:51
 */
const val checkAll = 256

class DefaultHolder(vEmpty: View, clRoot: ConstraintLayout, phRt: Placeholder) :
    HolderProvider(vEmpty, clRoot, phRt) {

    fun showCheck() {
        showView(checkAll)
    }

    override fun createView(context: Context, type: Int): View {
        if (type == checkAll) {
            return CheckBox(context)
        }
        return View(context)
    }

}