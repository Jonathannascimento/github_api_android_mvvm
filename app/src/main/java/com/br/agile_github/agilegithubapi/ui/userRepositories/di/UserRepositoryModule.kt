package com.br.agile_github.agilegithubapi.ui.userRepositories.di

import android.support.v7.app.AppCompatActivity
import com.br.agile_github.agilegithubapi.model.User
import com.br.agile_github.agilegithubapi.ui.base.ActivityModule
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class UserRepositoryModule(activity: AppCompatActivity, val user: User) : ActivityModule(activity) {

    @Provides
    public fun provideUser(): User = user
}