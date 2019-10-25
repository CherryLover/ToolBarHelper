package me.monster.toolbarhelper.fragment

import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_detail.*
import me.monster.toolbarhelper.R
import me.monster.toolbarhelper.base.BaseFragment
import me.monster.toolbarhelper.toolview.nav.NavPop

/**
 * @description
 * @author: Created jiangjiwei in 2019-09-25 00:01
 */
class DetailFragment : BaseFragment() {
    override fun init() {
        tv_detail_content.setOnClickListener { toast(getString(R.string.message_detail_content)) }
        toolHelper?.setPopProvider(NavPop)
    }

    override fun getTitle(): String {
        return getString(R.string.title_detail_frag)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_detail
    }

    override fun closeToolView(rootView: View): View? {
        return rootView.findViewById<TextView>(R.id.tv_detail_content)
    }
}