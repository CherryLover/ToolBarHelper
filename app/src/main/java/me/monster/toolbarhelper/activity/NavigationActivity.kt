package me.monster.toolbarhelper.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import me.monster.toolbarhelper.R
import me.monster.toolbarhelper.base.BaseActivity


class NavigationActivity : BaseActivity() {

    companion object {
        fun start(context: Context) {
            val stater = Intent(context, NavigationActivity::class.java)
            context.startActivity(stater)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
    }
}
