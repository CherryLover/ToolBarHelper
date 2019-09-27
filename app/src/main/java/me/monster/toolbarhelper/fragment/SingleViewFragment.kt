package me.monster.toolbarhelper.fragment

import android.view.View
import me.monster.toolbarhelper.R
import me.monster.toolbarhelper.base.BaseFragment

/**
 * @description
 * @author: Created jiangjiwei in 2019-09-27 17:03
 */
class SingleViewFragment : BaseFragment() {
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
}