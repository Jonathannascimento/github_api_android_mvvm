package com.br.agile_github.agilegithubapi.ui.searchUser

import android.content.Context
import com.br.agile_github.agilegithubapi.R
import com.br.agile_github.agilegithubapi.data.network.NetworkInteractor
import com.br.agile_github.agilegithubapi.data.remote.GithubApi
import com.br.agile_github.agilegithubapi.model.User
import com.br.agile_github.agilegithubapi.ui.base.BaseViewModel
import io.reactivex.Completable
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
 * ViewModel from [SearchUserActivity]
 * has as dependencies the parameters of the constructor, because in this screen a request is made.
 *
 */
class SearchUserViewModel @Inject constructor(
        val apiService: GithubApi,
        val networkInteractor: NetworkInteractor,
        val context: Context) : BaseViewModel() {

    /**
     * Disposable to start request to search [User].
     */
    private var networkRequest: Disposable = Disposables.disposed()

    /**
     * Behavior to be called when the user is found.
     */
    private var users: BehaviorSubject<User> = BehaviorSubject.create()

    /**
     * Make [User] request.
     */
    fun fetchUser(text: String) {

        dialogUtils.showOrHideProgressDialog()

        networkRequest =
                networkInteractor.hasNetworkConnectionCompletable()
                        .andThen(validateSearchString(text))
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
                                    else -> fetchErrors.onNext(getMessageErrorBody(e))
                                }
                            }

                            override fun onSuccess(value: User) {

                                dialogUtils.showOrHideProgressDialog()
                                users.onNext(value)
                            }
                        })
    }

    /**
     * Check is User typed an empty text in Edittext.
     */
    private fun validateSearchString(text: String): Completable {
        return if (text.isEmpty()) Completable.error(Throwable(context.getString(R.string.txt_err_search_empty))) else Completable.complete()
    }

    fun getUser(): Observable<User> = users.hide()
}