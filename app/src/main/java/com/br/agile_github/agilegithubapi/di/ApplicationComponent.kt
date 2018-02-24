package com.br.agile_github.agilegithubapi.di

import com.br.agile_github.agilegithubapi.ProjectApplication
import com.br.agile_github.agilegithubapi.data.network.NetworkModule
import com.br.agile_github.agilegithubapi.data.remote.ApiModule
import com.br.agile_github.agilegithubapi.ui.detailRepositories.di.DetailRepositoryComponent
import com.br.agile_github.agilegithubapi.ui.detailRepositories.di.DetailRepositoryModule
import com.br.agile_github.agilegithubapi.ui.searchUser.di.SearchUserComponent
import com.br.agile_github.agilegithubapi.ui.searchUser.di.SearchUserModule
import com.br.agile_github.agilegithubapi.ui.userRepositories.di.UserRepositoryComponent
import com.br.agile_github.agilegithubapi.ui.userRepositories.di.UserRepositoryModule
import dagger.Component
import javax.inject.Singleton


/**
 * A group of *di*.
 *
 * Main component of the application, provides the modules that will be used in the application
 *
 */
@Singleton
@Component(modules = arrayOf(
        ApplicationModule::class,
        NetworkModule::class,
        ApiModule::class
))
interface ApplicationComponent {

    // Inject Modules
    fun injectTo(app: ProjectApplication)

    // Inject SubModules
    fun injectSub(module: UserRepositoryModule): UserRepositoryComponent

    fun injectSub(module: SearchUserModule): SearchUserComponent

    fun injectSub(module: DetailRepositoryModule): DetailRepositoryComponent
}