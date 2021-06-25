package com.food.foodchallenge.ui.searchflow

import androidx.annotation.VisibleForTesting
import com.food.foodchallenge.database.MealRepository
import com.food.foodchallenge.network.NetworkClient
import com.food.foodchallenge.utility.toSearchItemsFromServer
import com.food.foodchallenge.utility.toSearchItemsFromStorage
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class SearchInteractor @Inject constructor(
    private val networkClient: NetworkClient,
    private val mealRepository: MealRepository
) {
    fun queryMeal(query: String): Single<List<SearchItem>> {
        if (query.isBlank()) return savedMeals()
        return serverMeal(query)
    }

    @VisibleForTesting
    fun serverMeal(query: String): Single<List<SearchItem>> {
        if (query.isBlank()) return Single.error(IllegalStateException("Query can't be blank"))
        return networkClient.searchMeals(query).map { it.toSearchItemsFromServer() }
    }

    @VisibleForTesting
    fun savedMeals(): Single<List<SearchItem>> {
        return mealRepository.getAll().map { it.toSearchItemsFromStorage() }
    }
}
