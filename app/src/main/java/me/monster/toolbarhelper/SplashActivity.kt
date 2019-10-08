package me.monster.toolbarhelper

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import kotlinx.android.synthetic.main.activity_splash.*
import me.monster.toolbarhelper.base.BaseActivity


class SplashActivity : BaseActivity() {

    override fun hideNavigation(): Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        getAni()
    }

    private fun delayEntrance() {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            MainActivity.start(this)
            this.finish()
        }, 800)
    }

    private fun getAni() {
        val scaleX = ObjectAnimator.ofFloat(iv_img_splash, "scaleX", 1F, 1.2F)
        scaleX.duration = 2000
        scaleX.interpolator = FastOutSlowInInterpolator()
        val scaleY = ObjectAnimator.ofFloat(iv_img_splash, "scaleY", 1F, 1.2F)
        scaleY.duration = 2000
        scaleY.interpolator = FastOutSlowInInterpolator()
        val set = AnimatorSet()
        set.playTogether(scaleX, scaleY)
        set.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(p0: Animator?) {
            }

            override fun onAnimationEnd(p0: Animator?) {
                delayEntrance()
            }

            override fun onAnimationCancel(p0: Animator?) {
            }

            override fun onAnimationStart(p0: Animator?) {
            }
        })
        set.start()

    }
}
