package com.br.agile_github.agilegithubapi.di

import com.br.agile_github.agilegithubapi.ProjectApplication
import com.br.agile_github.agilegithubapi.data.remote.ApiModule
import com.br.agile_github.agilegithubapi.ui.searchUser.di.SearchUserComponent
import com.br.agile_github.agilegithubapi.ui.searchUser.di.SearchUserModule
import com.br.agile_github.agilegithubapi.ui.userRepositories.di.UserRepositoryComponent
import com.br.agile_github.agilegithubapi.ui.userRepositories.di.UserRepositoryModule
import dagger.Component
import com.br.agile_github.agilegithubapi.data.network.NetworkModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        ApplicationModule::class,
        NetworkModule::class,
        ApiModule::class
))
interface ProjectApplicationComponent {

    // Injectors
    fun injectTo(app: ProjectApplication)

    // Inject SubModules
    fun injectSub(module: UserRepositoryModule) : UserRepositoryComponent
    fun injectSub(module: SearchUserModule) : SearchUserComponent
}