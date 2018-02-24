package com.br.agile_github.agilegithubapi.data.network

import android.content.Context
import android.net.ConnectivityManager
import io.reactivex.Completable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * A group of *network*.
 *
 * This class checks the user's connection to the internet
 *
 */
@Singleton
class NetworkInteractorImpl @Inject constructor(
        private val connectivityManager: ConnectivityManager, private val context: Context
) : NetworkInteractor {

    override fun hasNetworkConnection(): Boolean =
            connectivityManager.activeNetworkInfo?.isConnectedOrConnecting ?: false

    /**
     * check Connection.
     * @return Completable indicating that the requisition process may follow.
     */
    override fun hasNetworkConnectionCompletable(): Completable =
            if (hasNetworkConnection()) {
                Completable.complete()
            } else {
                Completable.error { NetworkInteractor.NetworkUnavailableException(context) }
            }
}