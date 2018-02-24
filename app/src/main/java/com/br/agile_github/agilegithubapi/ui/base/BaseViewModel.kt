package com.br.agile_github.agilegithubapi.ui.base

import android.databinding.BaseObservable
import com.br.agile_github.agilegithubapi.model.ErrorBodyRequisition
import com.br.agile_github.agilegithubapi.utils.DialogUtils
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

open class BaseViewModel : BaseObservable() {

    @Inject
    lateinit var dialogUtils: DialogUtils

    protected val fetchErrors: PublishSubject<Throwable> = PublishSubject.create()
    protected val networkErrors: PublishSubject<Throwable> = PublishSubject.create()

    protected fun getMessageErrorBody(e: Throwable): Throwable {

        if (e is HttpException) {

            val erroBody = e.response().errorBody()?.string()
            val bodyError: ErrorBodyRequisition = GsonBuilder().create().
                    fromJson(erroBody, ErrorBodyRequisition::class.java)
            return Throwable(bodyError.message)

        } else return e
    }

    fun remoteErrors(): Observable<Throwable> = networkErrors.hide()
    fun fetchErrors(): Observable<Throwable> = fetchErrors.hide()
}