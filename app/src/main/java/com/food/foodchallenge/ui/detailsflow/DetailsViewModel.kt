package com.food.foodchallenge.ui.detailsflow

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import com.food.foodchallenge.ui.BaseViewModel
import com.food.foodchallenge.utility.SchedulersProvider
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    val interactor: DetailsInteractor,
    schedulersProvider: SchedulersProvider
) : BaseViewModel(schedulersProvider) {

    val detailsItemState = MutableLiveData<DetailsItem>()

    fun detailsMeal(id: Int) {
        interactor.details(id)
            .applySchedulers()
            .subscribeAsUsual {
                updateUI(it)
            }
    }

    @VisibleForTesting
    fun updateUI(it: DetailsItem) {
        detailsItemState.value = it
    }
}