package me.monster.toolbarhelper


import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import me.monster.toolbarhelper.base.BaseFragment

/**
 * A simple [Fragment] subclass.
 */
class ListFragment : BaseFragment() {

    override fun init() {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            setTitle("测试列表")
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
