package com.br.agile_github.agilegithubapi.ui.userRepositories

import com.br.agile_github.agilegithubapi.data.network.NetworkInteractor
import com.br.agile_github.agilegithubapi.data.remote.GithubApi
import com.br.agile_github.agilegithubapi.model.Repository
import com.br.agile_github.agilegithubapi.ui.base.BaseViewModel
import javax.inject.Inject

class UserRepositoryViewModel @Inject internal constructor(
        private val apiService: GithubApi,
        private val networkInteractor: NetworkInteractor,
        private val repository: Repository) : BaseViewModel() {


}