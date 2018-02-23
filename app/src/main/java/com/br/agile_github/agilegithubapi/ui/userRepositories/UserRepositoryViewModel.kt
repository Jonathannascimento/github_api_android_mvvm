package com.br.agile_github.agilegithubapi.ui.userRepositories

import android.databinding.Bindable
import com.br.agile_github.agilegithubapi.data.network.NetworkInteractor
import com.br.agile_github.agilegithubapi.data.remote.GithubApi
import com.br.agile_github.agilegithubapi.model.ErrorBodyRequisition
import com.br.agile_github.agilegithubapi.model.Repository
import com.br.agile_github.agilegithubapi.model.User
import com.br.agile_github.agilegithubapi.ui.base.BaseViewModel
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject


class UserRepositoryViewModel @Inject internal constructor(
        private val apiService: GithubApi,
        private val networkInteractor: NetworkInteractor,
        private val user: User) : BaseViewModel() {

    private var networkRequest: Disposable = Disposables.disposed()

    private var repos: BehaviorSubject<List<Repository>> = BehaviorSubject.create()
    private val fetchErrors: PublishSubject<Throwable> = PublishSubject.create()
    private val networkErrors: PublishSubject<Throwable> = PublishSubject.create()

    fun fetchRepositories() {

        networkRequest =
                networkInteractor.hasNetworkConnectionCompletable()
                        .andThen(apiService.getUserRepositories(user.login!!))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe {
                            networkRequest.dispose() // Cancel any current running request
                        }
                        .subscribeWith(object : DisposableSingleObserver<List<Repository>>() {

                            override fun onSuccess(t: List<Repository>) {
                                repos.onNext(t)
                            }

                            override fun onError(e: Throwable) {
                                when (e) {
                                    is NetworkInteractor.NetworkUnavailableException -> networkErrors.onNext(e)
                                    else -> fetchErrors.onNext(getMessageErrorBody(e))
                                }
                            }
                        })
    }

    fun getRepositories(): Observable<List<Repository>> = repos.hide()
    fun remoteErrors(): Observable<Throwable> = networkErrors.hide()
    fun fetchErrors(): Observable<Throwable> = fetchErrors.hide()

    private fun getMessageErrorBody(e: Throwable): Throwable {

        val error = e as HttpException
        val erroBody = error.response().errorBody()?.string()
        val bodyError: ErrorBodyRequisition = GsonBuilder().create().
                fromJson(erroBody, ErrorBodyRequisition::class.java)

        return Throwable(bodyError.message)
    }

    @Bindable
    fun getUser() = user
}