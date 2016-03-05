package toru.io.wikotlin.intro

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
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

    override fun initView() {}

    override fun initToolbar() {}

    override fun getLayoutID(): Int {
        return R.layout.activity_intro
    }
}