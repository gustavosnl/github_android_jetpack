package com.glima.githubrepolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.glima.githubrepolist.databinding.FragmentFirstBinding
import org.koin.android.viewmodel.compat.ViewModelCompat.viewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    private val githubListViewModel by viewModel(this, GithubListViewModel::class.java)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFirstBinding.inflate(inflater)
        val adapter = RepositoryAdapter()

        binding.repositories.adapter = adapter
        githubListViewModel.repositories.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}