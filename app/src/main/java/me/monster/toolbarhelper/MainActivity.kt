package me.monster.toolbarhelper

import android.content.Context
import android.content.Intent
import android.os.Bundle
import me.monster.toolbarhelper.base.BaseActivity


class MainActivity : BaseActivity() {

    companion object {
        fun start(context: Context) {
            val stater = Intent(context, MainActivity::class.java)
            context.startActivity(stater)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
