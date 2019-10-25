package me.monster.toolbarhelper.fragment


import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_list.*
import me.monster.toolbarhelper.R
import me.monster.toolbarhelper.base.BaseFragment
import me.monster.toolbarhelper.toolview.BaseToolView
import me.monster.toolbarhelper.toolview.nav.NavPop
import me.monster.toolbarhelper.toolview.tools.ToolClickListener

/**
 * A simple [Fragment] subclass.
 */
class ListFragment : BaseFragment(), ToolClickListener {
    override fun onClick(what: Int) {
        when (what) {
            BaseToolView.navigation -> navClick()
            BaseToolView.title -> titleClick()
            BaseToolView.menu -> menuClick()
            else -> menuImgClick()
        }
    }

    private fun navClick() {
        toast("返回按钮的点击")
    }

    private fun titleClick() {
        toast("标题的点击")
    }

    private fun menuImgClick() {
        toast("菜单图标按钮的点击")
    }

    private fun menuClick() {
        toast("菜单按钮的点击")
    }

    override fun init() {
        toolHelper?.setListener(this)

        btn_list_entrance.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_listFragment_to_singleViewFragment)
        }

        val menuList = mutableListOf<Map<String, String>>()
        prepareListItem(menuList)
        val simpleAdapter = android.widget.SimpleAdapter(
            activity,
            menuList,
            android.R.layout.simple_list_item_1,
            arrayOf("text"),
            intArrayOf(android.R.id.text1)
        )
        lv_list_menu.adapter = simpleAdapter
        lv_list_menu.setOnItemClickListener { parent, view, position, id ->
            handleItemClick(position)
        }
    }

    private fun handleItemClick(position: Int) {
        when (position) {
            0 -> toolHelper?.setTitle("点击更换标题")
            1 -> toolHelper?.setMenu("详情")
            2 -> toolHelper?.setMenuImg(R.drawable.ic_send_black_24dp)
            3 -> toolHelper?.setBackgroundRes(R.drawable.bg_color_gradient)
            4 -> toolHelper?.setPopProvider(NavPop)
        }
    }

    private fun prepareListItem(menuList: MutableList<Map<String, String>>) {
        val map = hashMapOf("text" to "更换标题")
        val map1 = hashMapOf("text" to "更换右侧菜单")
        val map2 = hashMapOf("text" to "显示菜单图片")
        val map3 = hashMapOf("text" to "设置 ToolBar 背景（渐变色）")
        val map4 = hashMapOf("text" to "托管 ToolBar 返回按钮点击")
        menuList.add(map)
        menuList.add(map1)
        menuList.add(map2)
        menuList.add(map3)
        menuList.add(map4)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_list
    }

    override fun closeToolView(rootView: View): View? {
        return rootView.findViewById(R.id.lv_list_menu)
    }

    override fun getTitle(): String {
        return "默认标题"
    }
}
