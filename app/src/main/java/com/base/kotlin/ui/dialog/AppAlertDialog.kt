package com.base.kotlin.ui.dialog

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.TextView
import com.base.kotlin.R

class AppAlertDialog: AlertDialog {
    constructor(context: Context) : super(context)
    constructor(context: Context, themeResId: Int) : super(context, themeResId)
    constructor(context: Context, cancelable: Boolean, cancelListener: DialogInterface.OnCancelListener?) : super(
        context,
        cancelable,
        cancelListener
    )

    override fun onStart() {
        super.onStart()
        styleAlertDialog(context, this)
    }

    fun styleAlertDialog(context: Context, dialog: AppAlertDialog) {
        val resources = context.resources
        val dividerId = resources.getIdentifier("android:id/titleDivider", null, null)
        val divider = dialog.findViewById<View>(dividerId)
        divider?.setBackgroundColor(resources.getColor(R.color.color_red_700))
        val textViewId = resources.getIdentifier("android:id/alertTitle", null, null)
        val tv = dialog.findViewById<View>(textViewId) as TextView
        tv?.setTextColor(resources.getColor(R.color.color_blue_500))
    }
}