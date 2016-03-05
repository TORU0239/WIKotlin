package toru.io.wikotlin.framework.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by toru on 16. 3. 5..
 */
abstract class AbsBaseActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutID())
        initToolbar()
        initView()
    }

    abstract fun initView()
    abstract fun initToolbar()
    abstract fun getLayoutID():Int
}