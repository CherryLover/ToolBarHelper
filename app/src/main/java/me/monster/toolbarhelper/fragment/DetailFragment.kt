package me.monster.toolbarhelper.fragment

import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_detail.*
import me.monster.toolbarhelper.R
import me.monster.toolbarhelper.base.BaseFragment

/**
 * @description
 * @author: Created jiangjiwei in 2019-09-25 00:01
 */
class DetailFragment: BaseFragment() {
    override fun init() {
        tv_detail_content.setOnClickListener { toast("你点击了内容详情") }
    }

    override fun getTitle(): String {
        return "内容详情"
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_detail
    }

    override fun closeToolView(rootView: View): View? {
        return rootView.findViewById<TextView>(R.id.tv_detail_content)
    }
}