package com.br.agile_github.agilegithubapi.ui.userRepositories.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.br.agile_github.agilegithubapi.R
import com.br.agile_github.agilegithubapi.databinding.ItemViewRepositoryBinding
import com.br.agile_github.agilegithubapi.model.Repository
import javax.inject.Inject

/**
 * A group of *ui/userRepositories/adapter*.
 *
 * adapter to show list of [Repository].
 *
 */
class RepositoryAdapter @Inject constructor() : RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {

    private var repositories: List<Repository> = emptyList()
    private var itemClick: ((Repository) -> Unit)? = null


    /**
     * Inflate [RepositoryViewHolder] with databind.
     */
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RepositoryViewHolder {

        val binding = DataBindingUtil.inflate<ItemViewRepositoryBinding>(
                LayoutInflater.from(parent?.context),
                R.layout.item_view_repository,
                parent,
                false
        )

        return RepositoryViewHolder(binding)
    }

    /**
     * configure itemList.
     */
    override fun onBindViewHolder(holder: RepositoryViewHolder?, position: Int) {

        val binding = holder?.binding
        val repo = repositories[position]

        val viewModel = RepositoryViewModel(repo)
        binding?.viewModel = viewModel

        holder?.setClickListener(itemClick)
    }

    override fun getItemCount(): Int {
        return repositories.size
    }

    /**
     * listener to get onItemClickListener.
     */
    fun setClickListener(itemClick: ((Repository) -> Unit)?) {
        this.itemClick = itemClick
    }

    /**
     * update list of repository.
     */
    fun updateRepository(repositories : List<Repository>) {
        this.repositories = repositories
    }

    /**
     * View Holder of RecyclerView.
     */
    class RepositoryViewHolder(val binding: ItemViewRepositoryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setClickListener(callback: ((Repository) -> Unit)?) {
            binding.viewModel?.clicks()?.subscribe {
                callback?.invoke(binding.viewModel!!.repository)
            }
        }

    }
}