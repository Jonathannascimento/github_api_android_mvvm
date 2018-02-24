package com.br.agile_github.agilegithubapi.ui.userRepositories

import android.databinding.Bindable
import com.br.agile_github.agilegithubapi.data.network.NetworkInteractor
import com.br.agile_github.agilegithubapi.data.remote.GithubApi
import com.br.agile_github.agilegithubapi.model.Repository
import com.br.agile_github.agilegithubapi.model.User
import com.br.agile_github.agilegithubapi.ui.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject


/**
 * A group of *ui/detailRepositories*.
 *
 * ViewModel from [UserRepositoryActivity]
 * has as dependencies the parameters of the constructor, because in this screen a request is made.
 *
 */
class UserRepositoryViewModel @Inject internal constructor(
        private val apiService: GithubApi,
        private val networkInteractor: NetworkInteractor,
        private val user: User) : BaseViewModel() {

    /**
     * Disposable to start request to return [Repository]s.
     */
    private var networkRequest: Disposable = Disposables.disposed()

    /**
     * Behavior to be called when the user is found.
     */
    private var repos: BehaviorSubject<List<Repository>> = BehaviorSubject.create()

    /**
     * Make [Repository] request.
     */
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

    /**
     * Bind [User] to [UserRepositoryActivity] xml.
     */
    @Bindable
    fun getUser() = user
}