package com.br.agile_github.agilegithubapi.ui.base

import android.databinding.BaseObservable
import com.br.agile_github.agilegithubapi.utils.DialogUtils
import javax.inject.Inject

open class BaseViewModel : BaseObservable() {

    @Inject
    lateinit var dialogUtils: DialogUtils

}