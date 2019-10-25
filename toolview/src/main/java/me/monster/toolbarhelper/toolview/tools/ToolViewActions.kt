package me.monster.toolbarhelper.toolview.tools

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

/**
 * @description
 * @author: Created jiangjiwei in 2019-09-22 22:33
 */
interface ToolViewActions {
    fun setTitle(title: String)

    fun setMenu(menu: String)

    fun setMenuImg(@DrawableRes id: Int)

    fun setBgColor(@ColorRes id: Int)

}