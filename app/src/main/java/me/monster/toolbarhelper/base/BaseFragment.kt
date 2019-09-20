package me.monster.toolbarhelper.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import me.monster.toolbarhelper.ToolBarHelper

/**
 * @description
 * @author: Created jiangjiwei in 2019-09-20 14:29
 */
abstract class BaseFragment : Fragment() {
    lateinit var helper: ToolBarHelper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val closeToolView = closeToolView(view)
        if (closeToolView != null) {
            helper = ToolBarHelper(view, closeToolView,getTitle())
        }
        init()
    }

    /**
     * 设置 ToolBar 的 title
     */
    fun setTitle(title: String) {
        helper.setTitle(title)
    }

    /**
     * 执行内容该方法在 onViewCreated 方法中最后调用
     */
    abstract fun init()

    /**
     * 获取当前页面的标题，用于初始化 ToolBarHelper 时使用；
     */
    abstract fun getTitle(): String

    /**
     * 获取布局 Id 用于 onCreateView 加载布局时使用
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    /**
     * 用于获取最接近于 ToolBar 的 View 由子类指定
     */
    abstract fun closeToolView(rootView: View): View?
}