package toru.io.wikotlin.main

import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

import toru.io.wikotlin.R
import toru.io.wikotlin.framework.ui.AbsBaseActivity
import java.util.*
import kotlin.properties.Delegates

class MainActivity : AbsBaseActivity(){
    val TAG:String = "MainActivity"
    val ctx:Context = this

    // assign after instantiation
    var appListView: ListView by Delegates.notNull<ListView>()
    var appListAdapter: InstalledAppAdapter by Delegates.notNull<InstalledAppAdapter>()

    var appList: List<ApplicationInfo> by Delegates.notNull<List<ApplicationInfo>>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initView() {
        appList = getInstalledAppList()
        appListView = findViewById(R.id.app_listview) as ListView
        appListView.setOnItemClickListener { adapterView, view, i, l -> openApp(appList[i].packageName)}
        appListAdapter = InstalledAppAdapter(ctx, appList)

        appListView.adapter = appListAdapter
        appListAdapter.notifyDataSetChanged()
    }

    override fun initToolbar() { }

    override fun getLayoutID(): Int {
        return R.layout.activity_main
    }

    private fun getInstalledAppList(): List<ApplicationInfo> {
        return ctx.packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
    }

    private fun openApp(packageName:String): Boolean {
        var manager = ctx.packageManager;
        try {
            var intent:Intent? = manager.getLaunchIntentForPackage(packageName);
            if(intent == null) {
                return false
            }
            intent.addCategory(Intent.CATEGORY_LAUNCHER)
            ctx.startActivity(intent)
            return true
        } catch (e: PackageManager.NameNotFoundException) {
            return false
        }
    }
}
