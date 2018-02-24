package com.br.agile_github.agilegithubapi.data.remote

import com.br.agile_github.agilegithubapi.model.Repository
import com.br.agile_github.agilegithubapi.model.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * A group of *remote*.
 *
 * Github request API.
 *
 */
interface GithubApi {

    @GET("users/{username}")
    fun getUser(@Path("username") user: String): Single<User>

    @GET("users/{username}/repos")
    fun getUserRepositories(@Path("username") user: String): Single<List<Repository>>

    @GET("repos/{username}/{repository}/contributors")
    fun getUserRepositoriesContributors(@Path("username") user: String, @Path("repository") repository: String): Single<List<User>>
}