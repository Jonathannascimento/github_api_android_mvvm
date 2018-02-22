package com.br.agile_github.agilegithubapi.data.network

import android.net.ConnectivityManager
import com.br.agile_github.agilegithubapi.data.network.NetworkInteractor
import io.reactivex.Completable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkInteractorImpl @Inject constructor(
        private val connectivityManager: ConnectivityManager
) : NetworkInteractor {

    override fun hasNetworkConnection(): Boolean =
            connectivityManager.activeNetworkInfo?.isConnectedOrConnecting ?: false

    override fun hasNetworkConnectionCompletable(): Completable =
            if (hasNetworkConnection()) {
                Completable.complete()
            } else {
                Completable.error { NetworkInteractor.NetworkUnavailableException() }
            }

}