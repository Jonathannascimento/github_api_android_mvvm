package com.br.agile_github.agilegithubapi.ui.detailRepositories.di

import com.br.agile_github.agilegithubapi.ui.detailRepositories.DetailRepositoryActivity
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(
        DetailRepositoryModule::class
))
interface DetailRepositoryComponent {

    fun injectTo(activity: DetailRepositoryActivity)
}