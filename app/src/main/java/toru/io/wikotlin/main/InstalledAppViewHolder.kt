package toru.io.wikotlin.main

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import toru.io.wikotlin.R

/**
 * Created by toru on 16. 3. 5..
 */
class InstalledAppViewHolder(row: View)  {
    var installedAppIconDrawable: ImageView
    var installedAppNameText: TextView
    var installedAppPackageNameText:TextView

    // is it good code?
    init{
        installedAppIconDrawable = row.findViewById(R.id.app_icon_image) as ImageView
        installedAppNameText = row.findViewById(R.id.app_name_text) as TextView
        installedAppPackageNameText = row.findViewById(R.id.app_package_text) as TextView
    }
}