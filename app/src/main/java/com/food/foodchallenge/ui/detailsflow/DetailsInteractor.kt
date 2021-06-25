package com.food.foodchallenge.ui.detailsflow

import androidx.annotation.VisibleForTesting
import com.food.foodchallenge.database.MealRepository
import com.food.foodchallenge.network.NetworkClient
import com.food.foodchallenge.utility.toDetailsItemFromServer
import com.food.foodchallenge.utility.toDetailsItemFromStorage
import com.food.foodchallenge.utility.toMealStorage
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class DetailsInteractor @Inject constructor(
    private val networkClient: NetworkClient,
    private val mealRepository: MealRepository
) {

    fun details(id: Int): Single<DetailsItem> {
        return detailsServer(id)
            // getFromDB if some exception
            .onErrorResumeNext { detailsStorageSingle(id) }
    }

    fun detailsServer(id: Int): Single<DetailsItem> {
        return networkClient.detailsMeal(id)
            .map { it.toDetailsItemFromServer() }
            .doOnSuccess { mealRepository.insert(it.toMealStorage()) }
    }

    fun detailsStorageSingle(id: Int): Single<DetailsItem> {
        return mealRepository.getMeal(id)
            .map {
                it?.toDetailsItemFromStorage()
                    ?: throw IllegalStateException("Can't get meal from history")
            }
    }
}
