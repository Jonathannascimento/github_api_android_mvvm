package com.br.agile_github.agilegithubapi.ui.base

import android.support.v7.app.AppCompatActivity
import dagger.Module
import dagger.Provides

/**
 * A group of *ui/base*.
 *
 * provides the context of the current onscreen activity
 *
 *  @property activity current activity being shown.
 */
@Module
open class ActivityModule(val activity: AppCompatActivity) {

    @Provides
    fun provideActivity(): AppCompatActivity = activity
}