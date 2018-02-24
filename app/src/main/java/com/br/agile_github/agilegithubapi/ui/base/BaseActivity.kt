package com.br.agile_github.agilegithubapi.ui.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * A group of *ui/base*.
 *
 * This class provides useful instances for all activities, thus being inherited in all activities
 * of the project, it is responsible for providing an instance of the databind for the activity,
 * as well as its viewModel, being these instances passed through the generics B and the injection
 * of the current viewModel.
 *
 * @param B the current databind instance.
 * @param VM the current viewModel from activity.
 */
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

    /**
     * initial Configurations to be served in activity.
     */
    protected open fun loadData() {}

    /**
     * UI initial Configurations.
     */
    protected abstract fun initUI()

    /**
     * Inject the activity to the [mViewModel].
     */
    protected abstract fun initInjector()

    /**
     * Inflate the layout to the activity.
     */
    @LayoutRes protected abstract fun initContentView(): Int
}