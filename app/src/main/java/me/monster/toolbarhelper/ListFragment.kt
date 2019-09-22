package me.monster.toolbarhelper


import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import me.monster.toolbarhelper.base.BaseFragment
import me.monster.toolbarhelper.tools.ToolClickListener
import me.monster.toolbarhelper.view.ToolView

/**
 * A simple [Fragment] subclass.
 */
class ListFragment : BaseFragment(), ToolClickListener {
    override fun onClick(what: Int) {
        toast(
            when (what) {
                ToolView.navigation -> "返回"
                ToolView.title -> "标题"
                ToolView.menu -> "菜单按钮"
                else -> "图标按钮"
            }
        )
    }

    override fun init() {
        toolHelper?.setListener(this)
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            toolHelper?.setTitle("测试列表")
            toolHelper?.setMenuImg(R.drawable.ic_send_black_24dp)
            toolHelper?.setMenu("详情")
        }, 10 * 1000)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_list
    }

    override fun closeToolView(rootView: View): View? {
        return rootView.findViewById(R.id.tv_list_label)
    }

    override fun getTitle(): String {
        return "10 秒后设置标题"
    }
}
