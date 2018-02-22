package com.br.agile_github.agilegithubapi.ui.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import javax.inject.Inject


abstract class BaseActivity <B : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {

    protected var mDataBinding: B? = null

    @Inject
    lateinit var mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDataBinding = DataBindingUtil.setContentView(this, initContentView())
        initInjector()
        initUI()
        loadData()
    }
    protected fun loadData() {}
    protected abstract fun initUI()
    protected abstract fun initInjector()
    @LayoutRes protected abstract fun initContentView(): Int
}