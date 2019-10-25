package me.monster.toolbarhelper.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.monster.toolbarhelper.R

/**
 * @description
 * @author: Created jiangjiwei in 2019-10-25 16:02
 */
class CustomToolViewFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_custom_tool_view, container, false)
    }
}