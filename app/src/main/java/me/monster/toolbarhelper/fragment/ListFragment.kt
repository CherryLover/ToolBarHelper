package me.monster.toolbarhelper.fragment


import android.view.View
import androidx.appcompat.app.AlertDialog
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
        toast(getString(R.string.message_list_nav))
    }

    private fun titleClick() {
        toast(getString(R.string.message_list_title))
    }

    private fun menuImgClick() {
        toast(getString(R.string.message_list_menu_img))
    }

    private fun menuClick() {
        toast(getString(R.string.message_list_menu))
    }

    override fun init() {
        toolHelper?.setListener(this)

        btn_list_entrance.setOnClickListener {
            showMessage(getString(R.string.single_tool_message), R.id.action_listFragment_to_singleViewFragment)
        }

        btn_list_custom.setOnClickListener {
            showMessage(getString(R.string.custom_tool_message), R.id.action_listFragment_to_customToolViewFragment)
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
            0 -> toolHelper?.setTitle(getString(R.string.title_list_after_click))
            1 -> toolHelper?.setMenu(getString(R.string.menu_list_after_click))
            2 -> toolHelper?.setMenuImg(R.drawable.ic_send_black_24dp)
            3 -> toolHelper?.setBackgroundRes(R.drawable.bg_color_gradient)
            4 -> toolHelper?.setPopProvider(NavPop)
        }
    }

    private fun prepareListItem(menuList: MutableList<Map<String, String>>) {
        val map = hashMapOf("text" to getString(R.string.item_list_change_title))
        val map1 = hashMapOf("text" to getString(R.string.item_list_change_menu))
        val map2 = hashMapOf("text" to getString(R.string.item_list_change_menu_img))
        val map3 = hashMapOf("text" to getString(R.string.item_list_change_background))
        val map4 = hashMapOf("text" to getString(R.string.item_list_change_nav_click))
        menuList.add(map)
        menuList.add(map1)
        menuList.add(map2)
        menuList.add(map3)
        menuList.add(map4)
    }

    private fun showMessage(message: String, destId: Int) {
        AlertDialog.Builder(context!!)
            .setTitle(getString(R.string.title_app_tip))
            .setMessage(message)
            .setPositiveButton(getString(R.string.text_dialog_positive)) { _, _ -> Navigation.findNavController(view!!).navigate(destId) }
            .create()
            .show()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_list
    }

    override fun closeToolView(rootView: View): View? {
        return rootView.findViewById(R.id.lv_list_menu)
    }

    override fun getTitle(): String {
        return getString(R.string.title_list_default)
    }
}
