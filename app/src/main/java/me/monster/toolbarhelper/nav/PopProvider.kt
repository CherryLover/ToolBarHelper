package me.monster.toolbarhelper.nav

import android.content.ContextWrapper
import android.view.View
import androidx.appcompat.app.AppCompatActivity

/**
 * @description
 * @author: Created jiangjiwei in 2019-09-24 23:50
 */
abstract class PopProvider {
    abstract fun pop(view: View)

    open fun getActivity(view: View): AppCompatActivity? {
        var context = view.context
        while (context is ContextWrapper) {
            if (context is AppCompatActivity) {
                return context
            }
            context = context.baseContext
        }
        return null
    }
}