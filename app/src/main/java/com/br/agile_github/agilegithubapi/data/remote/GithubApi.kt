package com.br.agile_github.agilegithubapi.data.remote

import com.br.agile_github.agilegithubapi.model.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {

    @GET("users/{username}")
    fun getUser(@Path("username") user: String): Single<User>
}