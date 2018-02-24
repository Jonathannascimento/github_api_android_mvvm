package com.br.agile_github.agilegithubapi

import android.app.Application
import com.br.agile_github.agilegithubapi.di.ApplicationComponent
import com.br.agile_github.agilegithubapi.di.ApplicationModule
import com.br.agile_github.agilegithubapi.di.DaggerApplicationComponent
import dagger.Lazy
import timber.log.Timber
import javax.inject.Inject

class ProjectApplication : Application() {


    @Inject
    lateinit var debugTree: Lazy<Timber.DebugTree>

    /**
     * Provides a static instance of the application so you can inject the views
     */
    companion object {
        lateinit var graph: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()

        initDependencyGraph()

        /**
         * Betters Logs Started
         */
        if (BuildConfig.DEBUG) {
            Timber.plant(debugTree.get())
        }
    }

    /**
     * Initiate Daggers injections
     */
    private fun initDependencyGraph() {

        graph = DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this))
                .build()

        graph.injectTo(this)
    }

}