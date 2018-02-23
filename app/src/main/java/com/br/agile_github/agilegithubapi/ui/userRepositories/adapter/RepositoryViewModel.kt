package com.br.agile_github.agilegithubapi.ui.userRepositories.adapter

import android.databinding.Bindable
import com.br.agile_github.agilegithubapi.model.Repository
import com.br.agile_github.agilegithubapi.ui.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class RepositoryViewModel(val repository: Repository) : BaseViewModel() {

    private val clicks = PublishSubject.create<Unit>()

    fun onClick() {
        clicks.onNext(Unit)
    }

    @Bindable
    fun getRepo() = repository

    fun clicks(): Observable<Unit> = clicks.hide()


}