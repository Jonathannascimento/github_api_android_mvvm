package com.br.agile_github.agilegithubapi.di

import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import com.br.agile_github.agilegithubapi.ProjectApplication
import dagger.Module
import dagger.Provides
import timber.log.Timber
import javax.inject.Singleton

/**
 * A group of *di*.
 *
 * Module that provides some useful instances for the application, such as context and resources
 *
 * @property app the instance os applications to provides resources.
 */
@Module
class ApplicationModule(private val app: ProjectApplication) {

    @Provides @Singleton
    fun provideApplication(): Application = app

    @Provides @Singleton
    fun provideContext(): Context = app.baseContext

    @Provides @Singleton
    fun provideResources(): Resources = app.resources

    @Provides @Singleton
    fun provideLayoutInflater(context: Context): LayoutInflater {
        return LayoutInflater.from(context)
    }

    @Provides
    fun provideDebugTree(): Timber.DebugTree = Timber.DebugTree()
}