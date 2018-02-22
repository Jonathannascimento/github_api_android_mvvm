package com.br.agile_github.agilegithubapi.ui.searchUser.di

import com.br.agile_github.agilegithubapi.ui.searchUser.SearchUserActivity
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(
        SearchUserModule::class
))
interface SearchUserComponent {

    fun injectTo(activity: SearchUserActivity)

}