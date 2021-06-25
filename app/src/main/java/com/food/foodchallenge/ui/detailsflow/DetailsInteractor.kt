package com.food.foodchallenge.ui.detailsflow

import com.food.foodchallenge.network.NetworkClient
import com.food.foodchallenge.utility.toDetailsItemFromServer
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class DetailsInteractor @Inject constructor(
    private val networkClient: NetworkClient,
) {
    fun details(id: Int): Single<DetailsItem> {
        return detailsServer(id)
    }

    fun detailsServer(id: Int): Single<DetailsItem> {
        return networkClient.detailsMeal(id)
            .map { it.toDetailsItemFromServer() }
    }
}
