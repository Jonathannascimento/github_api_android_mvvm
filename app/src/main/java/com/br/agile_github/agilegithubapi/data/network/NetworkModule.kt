package com.br.agile_github.agilegithubapi.data.network

import android.content.Context
import android.net.ConnectivityManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * A group of *network*.
 *
 * Module that provides the instance of NetworkInteractor for use in the application
 *
 */
@Module
class NetworkModule {

    @Provides @Singleton
    fun provideConnectivityManager(context: Context): ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    @Provides @Singleton
    fun provideNetworkInteractor(networkInteractorImpl: NetworkInteractorImpl): NetworkInteractor = networkInteractorImpl

}