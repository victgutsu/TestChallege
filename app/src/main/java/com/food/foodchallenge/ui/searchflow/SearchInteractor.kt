package com.food.foodchallenge.ui.searchflow

import androidx.annotation.VisibleForTesting
import com.food.foodchallenge.network.NetworkClient
import com.food.foodchallenge.utility.toSearchItemsFromServer
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class SearchInteractor @Inject constructor(
    private val networkClient: NetworkClient
) {
    fun queryMeal(query: String): Single<List<SearchItem>> {
        return serverMeal(query)
    }

    @VisibleForTesting
    fun serverMeal(query: String): Single<List<SearchItem>> {
        if (query.isBlank()) return Single.error(IllegalStateException("Query can't be blank"))
        return networkClient.searchMeals(query).map { it.toSearchItemsFromServer() }
    }
}
