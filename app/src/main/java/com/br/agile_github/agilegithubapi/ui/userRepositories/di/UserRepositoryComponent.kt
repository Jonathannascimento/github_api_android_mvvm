package com.br.agile_github.agilegithubapi.ui.userRepositories.di

import com.br.agile_github.agilegithubapi.ui.userRepositories.UserRepositoryActivity
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(
        UserRepositoryModule::class
))
interface UserRepositoryComponent {

    fun injectTo(activity: UserRepositoryActivity)
}