package me.monster.toolbarhelper.fragment


import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import me.monster.toolbarhelper.R
import me.monster.toolbarhelper.base.BaseFragment
import me.monster.toolbarhelper.tools.ToolClickListener
import me.monster.toolbarhelper.view.ToolView

/**
 * A simple [Fragment] subclass.
 */
class ListFragment : BaseFragment(), ToolClickListener {
    override fun onClick(what: Int) {
        when (what) {
            ToolView.navigation -> navClick()
            ToolView.title -> titleClick()
            ToolView.menu -> menuClick()
            else -> menuImgClick()
        }
    }

    private fun navClick() {
        // TODO: 2019-09-24 返回按钮的处理
        toast("返回")
    }

    private fun titleClick() {
        toast("标题")
    }

    private fun menuImgClick() {
        toast("图标按钮")
    }

    private fun menuClick() {
        // TODO: 2019-09-24 点击跳转到没有动态设置 ToolView 的页面
        toast("菜单按钮")
    }

    override fun init() {
        toolHelper?.setListener(this)
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            toolHelper?.setTitle("测试列表")
            toolHelper?.setMenuImg(R.drawable.ic_send_black_24dp)
            toolHelper?.setMenu("详情")
        }, 3 * 1000)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_list
    }

    override fun closeToolView(rootView: View): View? {
        return rootView.findViewById(R.id.tv_list_label)
    }

    override fun getTitle(): String {
        return "3 秒后设置标题"
    }
}
