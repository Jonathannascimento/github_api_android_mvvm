package com.br.agile_github.agilegithubapi.ui.detailRepositories

import android.view.View
import com.br.agile_github.agilegithubapi.ProjectApplication
import com.br.agile_github.agilegithubapi.R
import com.br.agile_github.agilegithubapi.databinding.ActivityDetailRepositoryBinding
import com.br.agile_github.agilegithubapi.extensions.enableToolbarBackButton
import com.br.agile_github.agilegithubapi.model.Repository
import com.br.agile_github.agilegithubapi.model.User
import com.br.agile_github.agilegithubapi.ui.base.BaseActivity
import com.br.agile_github.agilegithubapi.ui.detailRepositories.adapter.ContributorsAdapter
import com.br.agile_github.agilegithubapi.ui.detailRepositories.di.DetailRepositoryModule
import com.br.agile_github.agilegithubapi.utils.Constants
import javax.inject.Inject

class DetailRepositoryActivity : BaseActivity<ActivityDetailRepositoryBinding, DetailRepositoryViewModel>() {

    @Inject
    lateinit var adapter: ContributorsAdapter

    private val user by lazy { intent.getParcelableExtra<User>(Constants.USER_REPOSITORY_PARAM) }
    private val repository by lazy { intent.getParcelableExtra<Repository>(Constants.USER_REPOSITORY_DETAIL_PARAM) }

    override fun initUI() {
        mDataBinding?.viewModel = mViewModel
        setSupportActionBar(mDataBinding?.toolbar)
        enableToolbarBackButton()
        configureRequestRxResponse()
        mViewModel.fetchContributors()
    }

    override fun initInjector() {
        ProjectApplication.graph.injectSub(DetailRepositoryModule(this, repository, user)).injectTo(this)
    }

    override fun initContentView(): Int {
        return R.layout.activity_detail_repository
    }

    private fun setupAdapter(users: List<User>) {

        if (users.isEmpty())
            mDataBinding?.txtEmptyContributors?.visibility = View.VISIBLE

        adapter.updateRepository(users)
        mDataBinding!!.recyclerView.adapter = adapter
    }

    private fun configureRequestRxResponse() {

        disposables.add(mViewModel.getRepositories().subscribe {
            mDataBinding!!.progressBar.visibility = View.INVISIBLE
            setupAdapter(it)
        })

        disposables.add(mViewModel.remoteErrors().subscribe {

            mDataBinding!!.progressBar.visibility = View.INVISIBLE
            mViewModel.dialogUtils.showAlertDialog(it.message!!, getString(R.string.txt_connection_error))
        })

        disposables.add(mViewModel.fetchErrors().subscribe {

            mDataBinding!!.progressBar.visibility = View.INVISIBLE
            mViewModel.dialogUtils.showAlertDialog(it.message!!, getString(R.string.txt_default_error))
        })
    }
}
