package com.br.agile_github.agilegithubapi.ui.userRepositories.adapter

import android.databinding.Bindable
import com.br.agile_github.agilegithubapi.model.Repository
import com.br.agile_github.agilegithubapi.ui.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * A group of *ui/userRepositories/adapter*.
 *
 * ViewModel of [RepositoryAdapter.RepositoryViewHolder].
 *
 */
class RepositoryViewModel(val repository: Repository) : BaseViewModel() {

    /**
     * publish to capture click events.
     */
    private val clicks = PublishSubject.create<Unit>()

    /**
     * event onClickListener.
     */
    fun onClick() {
        clicks.onNext(Unit)
    }

    /**
     * bind to itemView xml.
     */
    @Bindable
    fun getRepo() = repository

    fun clicks(): Observable<Unit> = clicks.hide()
}