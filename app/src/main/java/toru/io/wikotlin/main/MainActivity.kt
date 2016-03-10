package toru.io.wikotlin.main

import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.net.Uri
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

        appListView.setOnItemLongClickListener { adapterView, view, i, l ->
            AppSeletectActionDialog(ctx, appCommandList, object:OnDialogItemSelectListener{
                override fun onSelectedAction(position: Int) {
                    when(position){
                        0 -> openAppInfo(appList[i].packageName)
                        1 -> deleteApp(appList[i].packageName)
                    }
                }

            }).show()
            return@setOnItemLongClickListener true }

        appListAdapter = InstalledAppAdapter(ctx, appList)

        appListView.adapter = appListAdapter
        appListAdapter.notifyDataSetChanged()

        appCommandList.add("Open App Info Page")
        appCommandList.add("Delete selected app")
    }

    override fun initToolbar() { }

    override fun getLayoutID(): Int {
        return R.layout.activity_main
    }

    private fun getInstalledAppList(): List<ApplicationInfo> {

        var tempList:List<ApplicationInfo> = ctx.packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
        var installedAppList:ArrayList<ApplicationInfo>  = ArrayList<ApplicationInfo>()
        // check system app or not
        for(info in tempList){
            if((info.flags and ApplicationInfo.FLAG_SYSTEM) == 0){
                installedAppList.add(info)
            }
        }
        return installedAppList
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

    private fun openAppInfo(packageName:String){
        var appInfoIntent = Intent()
        appInfoIntent.action = android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        appInfoIntent.data = Uri.parse("package:" + packageName)
        startActivity(appInfoIntent)
    }

    private fun deleteApp(packageName:String){
        var deleteAppUri = Uri.parse("package:" + packageName)
        var deleteIntent = Intent(Intent.ACTION_DELETE).setData(deleteAppUri)
        startActivity(deleteIntent)
    }

    interface OnDialogItemSelectListener {
        fun onSelectedAction(position:Int)
    }
}