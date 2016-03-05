package toru.io.wikotlin.main

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import toru.io.wikotlin.R
import java.util.*
import kotlin.properties.Delegates

/**
 * Created by toru on 16. 3. 5..
 */
class InstalledAppAdapter : BaseAdapter{

    var ctx:Context by Delegates.notNull<Context>()
    var appList: ArrayList<ApplicationInfo> by Delegates.notNull<ArrayList<ApplicationInfo>>()
    var packageManager:PackageManager by Delegates.notNull<PackageManager>()

    constructor(c:Context, list:List<ApplicationInfo>){
        ctx = c
        appList = list as ArrayList<ApplicationInfo>
        packageManager = c.packageManager
    }

    override fun getCount(): Int {
        return appList.size

    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var viewHolder:InstalledAppViewHolder
        var view:View?
        if(convertView == null){
            view =  LayoutInflater.from(ctx).inflate(R.layout.adapter_current_installed_app, parent, false)
            viewHolder = InstalledAppViewHolder(view)
            view.tag = viewHolder
        }
        else{
            view = convertView
            viewHolder = view.tag as InstalledAppViewHolder
        }

        viewHolder.installedAppIconDrawable.setImageDrawable(appList[position].loadIcon(packageManager))
        viewHolder.installedAppNameText.text = appList[position].loadLabel(packageManager)
        viewHolder.installedAppPackageNameText.text = appList[position].packageName

        return view
    }

    override fun getItem(position: Int): Any? {
        return appList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}