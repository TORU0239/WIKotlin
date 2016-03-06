package toru.io.wikotlin.main

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.Toast
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

    var appCommandList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initView() {
        appList = getInstalledAppList()
        appListView = findViewById(R.id.app_listview) as ListView
        appListView.isLongClickable = true
        appListView.setOnItemClickListener { adapterView, view, i, l ->
            openApp(appList[i].packageName)
        }
        appListView.setOnItemLongClickListener { parent, view, position, id ->
            var builder : AlertDialog.Builder = AlertDialog.Builder(ctx)
            builder.setTitle("Notice!")
                    .setMessage("App you selected will be deleted. Are you sure?")
                    .setNegativeButton("No",
                            DialogInterface.OnClickListener {
                                dialogInterface, i ->  dialogInterface.dismiss()})
                    .setPositiveButton("YES",
                            DialogInterface.OnClickListener {
                                dialogInterface, i -> deleteApp(appList[position].packageName)})
                    .create().show()

            return@setOnItemLongClickListener true
        }
        appListAdapter = InstalledAppAdapter(ctx, appList)

        appListView.adapter = appListAdapter
        appListAdapter.notifyDataSetChanged()

        appCommandList.add("Run selected app")
        appCommandList.add("Delete selected app")
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

    private fun deleteApp(packageName:String){
        var deleteAppUri = Uri.parse("package:" + packageName)
        var deleteIntent = Intent(Intent.ACTION_DELETE).setData(deleteAppUri)
        startActivity(deleteIntent)
    }
}