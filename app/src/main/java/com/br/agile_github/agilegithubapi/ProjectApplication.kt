package com.br.agile_github.agilegithubapi

import android.app.Activity
import android.app.Application
import com.br.agile_github.agilegithubapi.di.ApplicationModule
import com.br.agile_github.agilegithubapi.di.DaggerProjectApplicationComponent
import com.br.agile_github.agilegithubapi.di.ProjectApplicationComponent
import dagger.Lazy
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class ProjectApplication : Application() {

    @Inject
    lateinit var debugTree: Lazy<Timber.DebugTree>

    companion object {
        lateinit var graph: ProjectApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()

        initDependencyGraph()

        if (BuildConfig.DEBUG) {
            Timber.plant(debugTree.get())
        }
    }

    private fun initDependencyGraph() {

        graph = DaggerProjectApplicationComponent.builder().applicationModule(ApplicationModule(this))
                .build()

        graph.injectTo(this)
    }

}