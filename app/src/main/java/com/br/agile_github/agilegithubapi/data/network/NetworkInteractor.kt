package com.br.agile_github.agilegithubapi.data.network

import io.reactivex.Completable


interface NetworkInteractor {

    fun hasNetworkConnection(): Boolean

    fun hasNetworkConnectionCompletable(): Completable

    class NetworkUnavailableException : Throwable("A network error has occurred. Check your Internet connection   " +
            "and try again later.")
}