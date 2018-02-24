package com.br.agile_github.agilegithubapi.ui.detailRepositories.di

import android.support.v7.app.AppCompatActivity
import com.br.agile_github.agilegithubapi.model.Repository
import com.br.agile_github.agilegithubapi.model.User
import com.br.agile_github.agilegithubapi.ui.base.ActivityModule
import dagger.Module
import dagger.Provides

@Module
class DetailRepositoryModule(activity: AppCompatActivity,
                             val repository: Repository,
                             val user: User) : ActivityModule(activity) {

    @Provides fun provideRepository(): Repository = repository

    @Provides fun provideUser(): User = user
}