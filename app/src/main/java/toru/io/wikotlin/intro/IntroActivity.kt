package toru.io.wikotlin.intro

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.view.ViewCompat
import android.support.v4.view.ViewPropertyAnimatorCompat
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.TextView
import toru.io.wikotlin.R
import toru.io.wikotlin.framework.ui.AbsBaseActivity
import toru.io.wikotlin.main.MainActivity

class IntroActivity : AbsBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler().postDelayed(Runnable {
            startActivity(Intent(this, MainActivity::class.java))
            finish()}, 2000);
    }

    override fun initView() {
//        var logoText: TextView = findViewById(R.id.logo_text) as TextView
//        var btnGroup: ViewGroup = findViewById(R.id.container) as ViewGroup
//
//        ViewCompat.animate(logoText)
//                    .translationY(-250.0f)
//                    .setStartDelay(300)
//                    .setDuration(1000)
//                    .setInterpolator(DecelerateInterpolator(1.2f))
//                    .start()
//
//        btnGroup.visibility = View.VISIBLE
//
//        for(i in 0..btnGroup.childCount-1){
//            var btn = btnGroup.getChildAt(i)
//            var viewAnimator:ViewPropertyAnimatorCompat
//
//            viewAnimator = ViewCompat.animate(btn)
//                                        .translationY(-50.0f).alpha(1.0f)
//                                        .setStartDelay(((300 * i) + 500).toLong())
//                                        .setDuration(1000)
//            viewAnimator.interpolator = DecelerateInterpolator()
//            viewAnimator.start()
//        }
    }



    override fun initToolbar() {}

    override fun getLayoutID(): Int {
        return R.layout.activity_intro
    }
}