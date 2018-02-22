package io.github.plastix.kotlinboilerplate.data.network

import android.content.Context
import android.net.ConnectivityManager
import com.br.agile_github.agilegithubapi.data.network.NetworkInteractor
import com.br.agile_github.agilegithubapi.data.network.NetworkInteractorImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides @Singleton
    fun provideConnectivityManager(context: Context): ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    @Provides @Singleton
    fun provideNetworkInteractor(networkInteractorImpl: NetworkInteractorImpl): NetworkInteractor = networkInteractorImpl

}