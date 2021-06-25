package com.food.foodchallenge.ui.searchflow

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.food.foodchallenge.databinding.FragmentSearchBinding
import com.food.foodchallenge.ui.BaseFragment
import com.food.foodchallenge.ui.BaseViewModel
import com.jakewharton.rxbinding4.appcompat.queryTextChanges
import com.jakewharton.rxbinding4.swiperefreshlayout.refreshes
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

class SearchFragment : BaseFragment() {
    private var rxBindindingDisposable = CompositeDisposable()
    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(SearchViewModel::class.java)
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val searchAdapter = SearchAdapter().apply {
            setClickListener { viewModel.openItem(it) }
        }
        binding.searchResults.adapter = searchAdapter
        binding.searchView
            .queryTextChanges()
            .map { it.toString().trim() }
            .debounce(
                1000,
                TimeUnit.MILLISECONDS
            ) // stream will go down after 1 second inactivity of user
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { text ->
                    viewModel.search(text)
                },
                {
                    Log.e("queryTextChanges", "" + it.message)
                }
            ).apply { rxBindindingDisposable.add(this) }

        binding.swipeContainer
            .refreshes()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    viewModel.refresh()
                },
                {
                    Log.e("refreshes", "" + it.message)
                }
            ).apply { rxBindindingDisposable.add(this) }

        viewModel.progressEvent.observe(viewLifecycleOwner) {
            binding.swipeContainer.isRefreshing = it
        }
        viewModel.searchListState.observe(viewLifecycleOwner) {
            searchAdapter.submitList(it)
        }
        viewModel.openSearchEvent.observe(viewLifecycleOwner) {

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        rxBindindingDisposable.dispose()
    }
}