package me.monster.toolbarhelper.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_entrance.*
import me.monster.toolbarhelper.R
import me.monster.toolbarhelper.base.BaseActivity

/**
 * @description
 * @author: Created jiangjiwei in 2019-10-08 19:42
 */
class EntranceActivity : BaseActivity() {

    companion object {
        fun start(context: Context) {
            val stater = Intent(context, EntranceActivity::class.java)
            context.startActivity(stater)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrance)
        btn_entrance_nav.setOnClickListener {
            NavigationActivity.start(this)
        }
    }
}