package com.br.agile_github.agilegithubapi.ui.searchUser

import com.br.agile_github.agilegithubapi.data.network.NetworkInteractor
import com.br.agile_github.agilegithubapi.data.remote.GithubApi
import com.br.agile_github.agilegithubapi.model.User
import com.br.agile_github.agilegithubapi.ui.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject


class SearchUserViewModel @Inject constructor(
        val apiService: GithubApi,
        val networkInteractor: NetworkInteractor) : BaseViewModel() {

    private var networkRequest: Disposable = Disposables.disposed()

    private var repos: BehaviorSubject<User> = BehaviorSubject.create()
    private val fetchErrors: PublishSubject<Throwable> = PublishSubject.create()
    private val networkErrors: PublishSubject<Throwable> = PublishSubject.create()
    private val localValidations: PublishSubject<String> = PublishSubject.create()

    fun fetchUser(text: String) {

        dialogUtils.showOrHideProgressDialog()

        networkRequest = networkInteractor.hasNetworkConnectionCompletable()

                .andThen(apiService.getUser(text))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    networkRequest.dispose() // Cancel any current running request
                }
                .subscribeWith(object : DisposableSingleObserver<User>() {

                    override fun onError(e: Throwable) {

                        dialogUtils.showOrHideProgressDialog()

                        when (e) {
                            is NetworkInteractor.NetworkUnavailableException -> networkErrors.onNext(e)
                            else -> fetchErrors.onNext(e)
                        }
                    }

                    override fun onSuccess(value: User) {

                        dialogUtils.showOrHideProgressDialog()

                        repos.onNext(value)
                    }
                })
    }

    fun getUser(): Observable<User> = repos.hide()
    fun remoteErrors(): Observable<Throwable> = fetchErrors.hide()
    fun localValidations(): Observable<String> = localValidations.hide()
    fun connectionErrors(): Observable<Throwable> = networkErrors.hide()
}