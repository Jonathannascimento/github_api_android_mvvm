package com.br.agile_github.agilegithubapi.utils

import android.app.Dialog
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.view.Window
import com.br.agile_github.agilegithubapi.R
import com.br.agile_github.agilegithubapi.ui.base.BaseActivity
import javax.inject.Inject


class DialogUtils @Inject constructor(context: AppCompatActivity) {

    val dialog: Dialog = Dialog(context)

    init {

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_progress)
    }

    fun showOrHideProgressDialog() {
        if (!dialog.isShowing) dialog.show() else dialog.dismiss()
    }
}