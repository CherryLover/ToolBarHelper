package me.monster.toolbarhelper.toolview.nav

import android.view.View
import androidx.navigation.Navigation


/**
 * @description 使用 Navigation 进行页面返回，当返回失败时，根据当前 View 查找当前所属 Activity 进行关闭
 * @author: Created jiangjiwei in 2019-09-25 00:26
 */
object NavPop : PopProvider() {
    override fun pop(view: View) {
        val navigateUp = Navigation.findNavController(view).navigateUp()
        if (!navigateUp) {
            getActivity(view)?.finish()
        }
    }
}