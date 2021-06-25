package com.food.foodchallenge.dagger

import com.food.foodchallenge.ui.MainActivity
import com.food.foodchallenge.ui.detailsflow.DetailsFragment
import com.food.foodchallenge.ui.searchflow.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UiModule {
    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributesSearchFragment(): SearchFragment

    @ContributesAndroidInjector
    abstract fun contributesDetailsFragment(): DetailsFragment
}
