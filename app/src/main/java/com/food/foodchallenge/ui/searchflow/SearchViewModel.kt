package com.food.foodchallenge.ui.searchflow

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import com.food.foodchallenge.ui.BaseViewModel
import com.food.foodchallenge.utility.SchedulersProvider
import com.food.foodchallenge.utility.SingleLiveEvent
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    val searchInteractor: SearchInteractor,
    schedulersProvider: SchedulersProvider
) : BaseViewModel(schedulersProvider) {

    // search states
    val searchListState = MutableLiveData<List<SearchItem>>()
    val searchQueryState = MutableLiveData("")

    // helps to open details
    val openSearchEvent = SingleLiveEvent<OpenSearchState>()

    fun search(query: String) {
        searchInteractor.queryMeal(query)
            .applySchedulers()
            .doOnSubscribe { showProgress(true) }
            .doFinally { showProgress(false) }
            .subscribeAsUsual { searchList ->
                updateSearchList(searchList)
                updateQueryState(query)
            }
    }

    @VisibleForTesting
    fun updateSearchList(searchList: List<SearchItem>) {
        searchListState.value = searchList
    }

    @VisibleForTesting
    fun updateQueryState(query: String) {
        searchQueryState.value = query
    }

    fun openItem(searchItem: SearchItem) {
        openSearchEvent.value = OpenSearchState(searchItem.id)
    }

    fun refresh() {
        val searchQuery = searchQueryState.value ?: return
        search(searchQuery)
    }
}
