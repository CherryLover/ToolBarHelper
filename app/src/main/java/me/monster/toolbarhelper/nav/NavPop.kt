package me.monster.toolbarhelper.nav

import android.app.Activity
import android.content.ContextWrapper
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

    private fun getActivity(view: View): Activity? {
        var context = view.context
        while (context is ContextWrapper) {
            if (context is Activity) {
                return context
            }
            context = context.baseContext
        }
        return null
    }
}