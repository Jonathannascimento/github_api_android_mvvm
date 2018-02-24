package com.br.agile_github.agilegithubapi.ui.base

import android.support.v7.app.AppCompatActivity
import com.br.agile_github.agilegithubapi.utils.DialogUtils
import dagger.Module
import dagger.Provides

@Module
open class ActivityModule(val activity: AppCompatActivity) {

    @Provides
    fun provideActivity(): AppCompatActivity = activity

    @Provides
    fun provideDialog(): DialogUtils = DialogUtils(activity)
}