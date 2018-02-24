package com.br.agile_github.agilegithubapi.ui.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import com.br.agile_github.agilegithubapi.ProjectApplication
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


abstract class BaseActivity<B : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {

    val disposables: CompositeDisposable = CompositeDisposable()

    protected var mDataBinding: B? = null

    @Inject
    lateinit var mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadData()
        initInjector()
        mDataBinding = DataBindingUtil.setContentView(this, initContentView())
        initUI()
    }

    protected open fun loadData() {}
    protected abstract fun initUI()
    protected abstract fun initInjector()
    @LayoutRes protected abstract fun initContentView(): Int
}