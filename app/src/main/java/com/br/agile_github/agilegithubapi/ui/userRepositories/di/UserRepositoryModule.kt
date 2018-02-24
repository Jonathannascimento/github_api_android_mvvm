package com.br.agile_github.agilegithubapi.ui.userRepositories.di

import android.support.v7.app.AppCompatActivity
import com.br.agile_github.agilegithubapi.model.User
import com.br.agile_github.agilegithubapi.ui.base.ActivityModule
import dagger.Module
import dagger.Provides

@Module
class UserRepositoryModule(val user: User, activity: AppCompatActivity) : ActivityModule(activity){

    @Provides fun provideUser(): User = user
}