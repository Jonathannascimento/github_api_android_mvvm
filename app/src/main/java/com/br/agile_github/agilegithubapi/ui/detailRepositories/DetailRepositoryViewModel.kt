package com.br.agile_github.agilegithubapi.ui.detailRepositories

import com.br.agile_github.agilegithubapi.data.network.NetworkInteractor
import com.br.agile_github.agilegithubapi.data.remote.GithubApi
import com.br.agile_github.agilegithubapi.model.Repository
import com.br.agile_github.agilegithubapi.model.User
import com.br.agile_github.agilegithubapi.ui.base.BaseViewModel
import com.br.agile_github.agilegithubapi.utils.DialogUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class DetailRepositoryViewModel @Inject internal constructor(
        private val apiService: GithubApi,
        private val networkInteractor: NetworkInteractor,
        private val repository: Repository,
        private val user: User) : BaseViewModel() {

    private var networkRequest: Disposable = Disposables.disposed()

    private var constributors: BehaviorSubject<List<User>> = BehaviorSubject.create()

    fun fetchContributors() {

        networkRequest =
                networkInteractor.hasNetworkConnectionCompletable()
                        .andThen(apiService.getUserRepositoriesContributors(user.login!!, repository.name!!))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe {
                            networkRequest.dispose() // Cancel any current running request
                        }
                        .subscribeWith(object : DisposableSingleObserver<List<User>>() {
                            override fun onError(e: Throwable) {

                                when (e) {
                                    is NetworkInteractor.NetworkUnavailableException -> networkErrors.onNext(e)
                                    else -> fetchErrors.onNext(getMessageErrorBody(e))
                                }
                            }

                            override fun onSuccess(value: List<User>) {

                                constributors.onNext(value)
                            }
                        })
    }

    fun getRepositories(): Observable<List<User>> = constributors.hide()
}