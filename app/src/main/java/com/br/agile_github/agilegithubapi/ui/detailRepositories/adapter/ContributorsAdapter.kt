package com.br.agile_github.agilegithubapi.ui.detailRepositories.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.br.agile_github.agilegithubapi.R
import com.br.agile_github.agilegithubapi.databinding.ItemViewRepositoryContributorsBinding
import com.br.agile_github.agilegithubapi.model.User
import javax.inject.Inject

/**
 * A group of *ui/detailRepositories/adapter*.
 *
 * adapter to show list of Contributors].
 *
 */
class ContributorsAdapter @Inject constructor() : RecyclerView.Adapter<ContributorsAdapter.ContributorViewHolder>() {

    private var users: List<User> = emptyList()
    private var itemClick: ((User) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ContributorViewHolder {
        val binding = DataBindingUtil.inflate<ItemViewRepositoryContributorsBinding>(
                LayoutInflater.from(parent?.context),
                R.layout.item_view_repository_contributors,
                parent,
                false
        )

        return ContributorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContributorViewHolder?, position: Int) {

        val binding = holder?.binding
        val user = users[position]

        val viewModel = ContributorsViewModel(user)
        binding?.viewModel = viewModel

        holder?.setClickListener(itemClick)
    }

    fun setClickListener(itemClick: ((User) -> Unit)?) {
        this.itemClick = itemClick
    }

    fun updateRepository(repositories: List<User>) {
        this.users = repositories
    }

    override fun getItemCount(): Int {
        return users.size
    }

    class ContributorViewHolder(val binding: ItemViewRepositoryContributorsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setClickListener(callback: ((User) -> Unit)?) {
            binding.viewModel?.clicks()?.subscribe {
                callback?.invoke(binding.viewModel!!.user)
            }
        }
    }
}