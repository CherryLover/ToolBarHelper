package me.monster.toolbarhelper.nav

import android.view.View
import me.monster.toolbarhelper.tools.L

/**
 * @description
 * @author: Created jiangjiwei in 2019-09-25 00:38
 */
class RiggerPop() : PopProvider() {
    override fun pop(view: View) {
        val activity = getActivity(view)
        val fragments = activity?.supportFragmentManager?.fragments
        for (i in 0 until fragments?.size!!) {
            val fragment = fragments[i]
            if (fragment.isVisible) {
                L.d("当前显示的 Fragment 为：${fragment.tag}")
                // TODO: 2019-09-25
                // Rigger.get(fragment).onBackPressed()
            }
        }
    }
}