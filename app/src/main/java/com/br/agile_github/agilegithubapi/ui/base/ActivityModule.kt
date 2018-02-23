package com.br.agile_github.agilegithubapi.ui.base

import android.support.v7.app.AppCompatActivity
import dagger.Module
import dagger.Provides

@Module
open class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    fun provideActivity(): AppCompatActivity = activity

}