package toru.io.wikotlin.main

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import toru.io.wikotlin.R
import java.util.*

/**
 * Created by toru on 16. 3. 9..
 */
class AppSeletectActionDialog(var ctx: Context, var actionTextList: ArrayList<String>, var listener: MainActivity.OnDialogItemSelectListener) : Dialog(ctx){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_app_selected)

        var actionList : ListView = findViewById(R.id.listview_app_item_selected) as ListView
        var actionAdapter = ArrayAdapter<String>(ctx, android.R.layout.simple_list_item_1, actionTextList)
        actionList.adapter = actionAdapter

        actionList.setOnItemClickListener { adapterView, view, i, l -> listener.onSelectedAction(i) }
    }
}