package com.br.agile_github.agilegithubapi.data.network

import android.content.Context
import com.br.agile_github.agilegithubapi.R
import io.reactivex.Completable

/**
 * A group of *network*.
 *
 * This class is used to return if the device has internet or not
 *
 */
interface NetworkInteractor {

    fun hasNetworkConnection(): Boolean

    fun hasNetworkConnectionCompletable(): Completable

    class NetworkUnavailableException(context: Context) : Throwable(context.getString(R.string.txt_error_connection_message))
}