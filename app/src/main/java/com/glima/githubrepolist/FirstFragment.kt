package com.glima.githubrepolist

//        adapter.withLoadStateHeaderAndFooter(
//            header =,
//            footer =
//        )
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.glima.githubrepolist.databinding.FragmentFirstBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.compat.ViewModelCompat.viewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    private val githubListViewModel by viewModel(this, GithubListViewModel::class.java)
    private var job: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFirstBinding.inflate(inflater)
        val adapter = RepositoryAdapter()

        binding.repositories.adapter = adapter.withLoadStateFooter(
            footer = RepoLoadingStateAdapter(adapter::retry)
        )

        job?.cancel()
        job = lifecycleScope.launch {
            githubListViewModel.searchRepositories("kotlin").collectLatest {
                adapter.submitData(it)
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}