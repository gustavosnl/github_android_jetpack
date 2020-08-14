package com.glima.githubrepolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.glima.githubrepolist.databinding.ItemLoadingBinding

class RepoLoadingStateAdapter(val retry: () -> Unit) :
    LoadStateAdapter<RepoLoadingStateAdapter.LoadStateViewHolder>() {
    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = ItemLoadingBinding.inflate(LayoutInflater.from(parent.context))
        return LoadStateViewHolder(binding)
    }

    inner class LoadStateViewHolder(private val binding: ItemLoadingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            binding.loadState = loadState
            binding.executePendingBindings()

            binding.let {
                it.progressBar.isVisible = loadState is LoadState.Loading
                it.textView.isVisible = loadState is LoadState.Error

                it.button.let {
                    it.isVisible = loadState is LoadState.Error
                    it.setOnClickListener { retry.invoke() }
                }

            }
        }
    }
}