package com.br.agile_github.agilegithubapi.ui.searchUser

import android.content.Intent
import android.view.View
import com.br.agile_github.agilegithubapi.ProjectApplication
import com.br.agile_github.agilegithubapi.R
import com.br.agile_github.agilegithubapi.databinding.ActivitySearchUserBinding
import com.br.agile_github.agilegithubapi.model.User
import com.br.agile_github.agilegithubapi.ui.base.BaseActivity
import com.br.agile_github.agilegithubapi.ui.searchUser.di.SearchUserModule
import com.br.agile_github.agilegithubapi.ui.userRepositories.UserRepositoryActivity
import com.br.agile_github.agilegithubapi.utils.Constants
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_search_user.*

class SearchUserActivity : BaseActivity<ActivitySearchUserBinding, SearchUserViewModel>(), View.OnClickListener {

    val disposables: CompositeDisposable = CompositeDisposable()

    override fun initContentView(): Int {
        return R.layout.activity_search_user
    }

    override fun initUI() {
        btn_search_user.setOnClickListener(this)
        configureRequestRxResponse()
    }

    private fun configureRequestRxResponse() {

        disposables.add(mViewModel.getUser().subscribe {
            callDetailActivity(it)
        })

        disposables.add(mViewModel.remoteErrors().subscribe {
            mViewModel.dialogUtils.showAlertDialog(it.message!!, getString(R.string.txt_connection_error))
        })

        disposables.add(mViewModel.fetchErrors().subscribe {
            mViewModel.dialogUtils.showAlertDialog(it.message!!, getString(R.string.txt_default_error))
        })
    }

    private fun callDetailActivity(user: User) {
        val intent = Intent(this, UserRepositoryActivity::class.java)
        intent.putExtra(Constants.USER_REPOSITORY_PARAM, user)
        startActivity(intent)
    }

    override fun onClick(v: View?) {
        mViewModel.fetchUser(edt_user_name.text.toString())
    }

    override fun initInjector() {
        ProjectApplication.graph.injectSub(SearchUserModule(this)).injectTo(this)
    }
}
