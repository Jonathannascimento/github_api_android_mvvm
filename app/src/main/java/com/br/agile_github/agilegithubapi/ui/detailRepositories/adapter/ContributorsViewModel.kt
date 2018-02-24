package com.br.agile_github.agilegithubapi.ui.detailRepositories.adapter

import android.databinding.Bindable
import com.br.agile_github.agilegithubapi.model.User
import com.br.agile_github.agilegithubapi.ui.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class ContributorsViewModel(val user: User) : BaseViewModel() {

    private val clicks = PublishSubject.create<Unit>()

    fun onClick() {
        clicks.onNext(Unit)
    }

    @Bindable
    fun getContributor() = user

    fun clicks(): Observable<Unit> = clicks.hide()
}